package com.tiantian.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.mapper.TbItemParamMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemExample;
import com.tiantian.pojo.TbItemParam;
import com.tiantian.pojo.TbItemParamExample;
import com.tiantian.pojo.TbItemParamExample.Criteria;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper mapper;
    
    @Override
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
        //1.设置分页
        PageHelper.startPage(page, rows);
        //2.查询
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = mapper.selectByExampleWithBLOBs(example);
        //3.获取分页结果
        PageInfo<TbItemParam> info = new PageInfo<>(list);
        //4.设置pojo
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TiantianResult getItemParamByCid(Long id) {
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(id);
        List<TbItemParam> list = mapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            TbItemParam itemParam = list.get(0);
            return TiantianResult.ok(itemParam);
        }
        return TiantianResult.ok();
    }

    @Override
    public TiantianResult insertItemParam(Long cid, String paramData) {
        //创建一个pojo
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入记录
        int num = mapper.insert(itemParam);
        return TiantianResult.ok();

    }
    
}
