package com.tiantian.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.IDUtils;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.mapper.TbItemDescMapper;
import com.tiantian.mapper.TbItemMapper;
import com.tiantian.mapper.TbItemParamItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemDesc;
import com.tiantian.pojo.TbItemExample;
import com.tiantian.pojo.TbItemParam;
import com.tiantian.pojo.TbItemParamItem;
import com.tiantian.pojo.TbItemParamItemExample;
import com.tiantian.pojo.TbItemParamItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    
    @Autowired
    private TbItemDescMapper itemDescMapper;
    
    @Autowired
    private TbItemMapper itemMapper;
    
    @Override
    public TbItem getItemById(Long id) {
        //根据主键查询
         TbItem item = itemMapper.selectByPrimaryKey(id);
        //用查询条件去查询
       /* //1.创建example
        TbItemExample example = new TbItemExample();
        //2.获得criteria
        Criteria criteria = example.createCriteria();
        //3.构造criteria
        criteria.andIdEqualTo(id);
        //4.传入example到mapper，获取查询list
        List<TbItem> list = itemMapper.selectByExample(example);
        //5.判断非空，并取出对象
        TbItem item = null;
        if(list != null && list.size() > 0){
            item = list.get(0);
        }*/
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //1.设置分页
        PageHelper.startPage(page, rows);
        //2.查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //3.获取分页结果
        PageInfo<TbItem> info = new PageInfo<>(list);
        //4.设置pojo
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(list);
        return result;
    }

    //包含事务控制
    @Override
    public TiantianResult createItem(TbItem item, String desc,String itemParam) {
        //1.补全item
        //id
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
         // '商品状态，1-正常，2-下架，3-删除'
        item.setStatus((byte)1);
         // 创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //2.写入数据库
        itemMapper.insert(item);
        //3.补全ItemDesc
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //4.写入数据库
        itemDescMapper.insert(itemDesc);
        
        //保存规格模板
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        itemParamItem.setParamData(itemParam);
        itemParamItemMapper.insert(itemParamItem);
        
        //返回状态
        return TiantianResult.ok();     
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        //1.根据id查询item-param-item里面的规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
       //2.判断查询结果
        if(list == null || list.isEmpty()){
            return "";
        }
        //3.取规格参数
        TbItemParamItem itemParamItem = list.get(0);
        //4.取json数据，并转换为java对象
        String paramData = itemParamItem.getParamData();
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
        //5.遍历list生成html
        StringBuffer sb = new StringBuffer();
        
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("     <tbody>\n");
        for (Map map : mapList) {
                sb.append("             <tr>\n");
                sb.append("                     <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
                sb.append("             </tr>\n");
                //取规格项
                List<Map>mapList2 = (List<Map>) map.get("params");
                for (Map map2 : mapList2) {
                        sb.append("             <tr>\n");
                        sb.append("                     <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                        sb.append("                     <td>"+map2.get("v")+"</td>\n");
                        sb.append("             </tr>\n");
                }
        }
        sb.append("     </tbody>\n");
        sb.append("</table>");
        
        return sb.toString();

    }


}
