package com.tiantian.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.mapper.TbContentMapper;
import com.tiantian.pojo.TbContent;
import com.tiantian.pojo.TbContentExample;
import com.tiantian.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    
    @Override
    public List<TbContent> getContentList(Long cid) {
        //检索
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExample(example);
        return list;
    }

}
