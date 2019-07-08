package com.tiantian.service;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;

public interface ItemParamService {

    EasyUIDataGridResult getItemParamList(Integer page, Integer rows);
    
    TiantianResult getItemParamByCid(Long id);
    
    TiantianResult insertItemParam(Long cid, String paramData);
}
