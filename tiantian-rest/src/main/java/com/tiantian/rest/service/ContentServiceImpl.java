package com.tiantian.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.mapper.TbContentMapper;
import com.tiantian.pojo.TbContent;
import com.tiantian.pojo.TbContentExample;
import com.tiantian.pojo.TbContentExample.Criteria;
import com.tiantian.rest.component.JedisClient;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    
    @Autowired
    private JedisClient jedisClient;
    
    @Value("REDIS_CONTENT_KEY")
    private String REDIS_CONTENT_KEY;
    
    @Override
    public List<TbContent> getContentList(Long cid) {
        //1.从缓存中请求数据
        //2.如果有直接返回
        try {
            //先从redis里取缓存数据
            String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
            if(!StringUtils.isBlank(json)){     //判断json取到的是否为空
                //把json转换成List
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.没找到
        
        //4.穿透查询
        //检索业务
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExample(example);
        
        
        //5.回种
        //返回结果之前。向缓存中添加这个数据
        try{
            //规范key，使用哈希set
            //定义一个保存content的key，hash中每一个field就是cid，值就是数据库查询后的list转换成json字符串
            jedisClient.hset(REDIS_CONTENT_KEY, cid + "",JsonUtils.objectToJson(list));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //6.返回数据
        return list;
    }

    @Override
    public TiantianResult syncContent(Long cid) {
        jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
        return TiantianResult.ok();
    }

}
