package com.tiantian.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.mapper.TbItemCatMapper;
import com.tiantian.pojo.TbItemCat;
import com.tiantian.pojo.TbItemCatExample;
import com.tiantian.pojo.TbItemCatExample.Criteria;
import com.tiantian.rest.pojo.CatNode;
import com.tiantian.rest.pojo.ItemCatResult;

/**
 * {
    "data": [
        {
            "u": "/products/1.html",
            "n": "<a href='/products/1.html'>图书、音像、电子书刊</a>",
            "i": [
                {
                    "u": "/products/2.html",
                    "n": "电子书刊",
                    "i": [
                        "/products/3.html|电子书",
                        "/products/4.html|网络原创",
                        "/products/5.html|数字杂志",
                        "/products/6.html|多媒体图书"
                    ]
                }]
          }
     }
 *   data对应的是 ItemCatResult对象
 *   i对应的是CatNode节点对象
 *   父节点中一级节点的"n"属性和其它父节点不同
 *   叶子节点的属性和其它节点不同，只有拼接成的String字符串
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    
    @Override
    public ItemCatResult getItemCatResutl() {
        //data对象
        ItemCatResult result = new ItemCatResult();
        result.setData(getItemList(0L));
        return result;
    }
    
    //递归获取节点对象的结果集
    public List getItemList(Long parentId){
        //1.根据parentId查询列表
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList<>();
        int index = 0; //计数器，限制一级父节点的个数。
        //2.遍历结果列表
        for (TbItemCat tbItemCat : list) {
            //限制个数
            if(index >= 14){
                break;
            }
            //3a.如果是父节点
            if(tbItemCat.getIsParent()){
                //4a.补全节点对象的属性
                CatNode node = new CatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");
                if(tbItemCat.getParentId() == 0){
                    node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                    //第一级父节点个数不能超过14个
                    index++;
                }else{
                    node.setName(tbItemCat.getName());
                }
                //5a.递归
                node.setItems(getItemList(tbItemCat.getId()));  //传入的自己的id，再寻找其子节点
                //6a.添加结果集中
                resultList.add(node);   //每个节点都会添加到结果集中。
            }else{
                //3b.如果是叶子节点(不必递归)
                //4b.补全属性
                String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
                //5b.添加结果集中
                resultList.add(item);
            }
        }
        //返回结果集
        return resultList;
    }

}
