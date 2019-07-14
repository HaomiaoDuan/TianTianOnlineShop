package com.tiantian.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiantian.common.utils.JsonUtils;
import com.tiantian.mapper.TbItemDescMapper;
import com.tiantian.mapper.TbItemMapper;
import com.tiantian.mapper.TbItemParamItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemDesc;
import com.tiantian.pojo.TbItemParamItem;
import com.tiantian.pojo.TbItemParamItemExample;
import com.tiantian.pojo.TbItemParamItemExample.Criteria;
import com.tiantian.rest.component.JedisClient;

/**
 * 商品管理
 * 
 * @author 89371
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;

    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;

    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;

    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;

    @Override
    public TbItem getItemById(Long itemId) {
        // 1a.查询缓存，如果有缓存，直接返回
        try {
            // 2.不能使用hash存redis，只能用特制的String键值对。
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_BASE_INFO_KEY);
            // 3.判断数据是否存在
            if (StringUtils.isNotBlank(json)) {
                // 4.把json数据转换成java对象
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1b.根据商品id查询商品基本信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        // 2b.向redis中添加缓存。
        // 添加缓存原则是不能影响正常的业务逻辑
        try {
            // 向redis中添加缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_BASE_INFO_KEY,
                    JsonUtils.objectToJson(item));
            // 设置key的过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_BASE_INFO_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        // 查询缓存
        // 查询缓存，如果有缓存，直接返回
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY);
            // 判断数据是否存在
            if (StringUtils.isNotBlank(json)) {
                // 把json数据转换成java对象
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return itemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据商品id查询商品详情
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        // 添加缓存
        try {
            // 向redis中添加缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY,
                    JsonUtils.objectToJson(itemDesc));
            // 设置key的过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }

    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        /**
         *  添加缓存逻辑查询缓存
         *  查询缓存
         *  如果有缓存，直接返回
         */
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY);
            // 判断数据是否存在
            if (StringUtils.isNotBlank(json)) {
                // 把json数据转换成java对象
                TbItemParamItem itemParamitem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return itemParamitem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据商品id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        // 取规格参数
        if (list != null && list.size() > 0) {
            TbItemParamItem itemParamItem = list.get(0);
            // 添加缓存
            try {
                // 向redis中添加缓存
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY,
                        JsonUtils.objectToJson(itemParamItem));
                // 设置key的过期时间
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemParamItem;
        }
        return null;
    }

}
