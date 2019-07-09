package com.tiantian.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.mapper.TbContentMapper;
import com.tiantian.pojo.TbContent;
import com.tiantian.pojo.TbContentExample;
import com.tiantian.pojo.TbContentExample.Criteria;
import com.tiantian.pojo.TbItemParam;
import com.tiantian.pojo.TbItemParamExample;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    
    @Override
    public EasyUIDataGridResult getContentListByCatId(Integer page, Integer rows, Long categoryId) {
        //1.设置分页
        //PageHelper.startPage(page, rows);
        //2.查询
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        if(list ==null){
            return null;
        }
        //3.获取分页结果
        //PageInfo<TbContent> info = new PageInfo<>(list);      //分页插件有问题，不管了
        //4.设置pojo
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        //result.setTotal(info.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TiantianResult insertContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入数据
        contentMapper.insert(content);
        return TiantianResult.ok();

    }

}
