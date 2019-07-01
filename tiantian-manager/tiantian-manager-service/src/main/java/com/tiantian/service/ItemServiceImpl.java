package com.tiantian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.mapper.TbItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemExample;
import com.tiantian.pojo.TbItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService {
    
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

}
