package com.tiantian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.mapper.TbItemCatMapper;
import com.tiantian.pojo.TbItemCat;
import com.tiantian.pojo.TbItemCatExample;
import com.tiantian.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper mapper;
    
    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //1.创建example
        TbItemCatExample example = new TbItemCatExample();
        //2.设置criteria 
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //3.查询
        List<TbItemCat> list = mapper.selectByExample(example);
        //4.转换成EasyUITreeNode
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode result = new EasyUITreeNode();
            result.setId(tbItemCat.getId());
            result.setText(tbItemCat.getName());
            result.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(result);
        }
        
        return resultList;
    }

}
