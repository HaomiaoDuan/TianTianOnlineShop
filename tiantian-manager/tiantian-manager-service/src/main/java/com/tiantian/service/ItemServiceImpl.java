package com.tiantian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.mapper.TbItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemExample;

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


}
