package com.tiantian.service;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.pojo.TbContent;

public interface ContentService {

    EasyUIDataGridResult getContentListByCatId(Integer page, Integer rows,Long categoryId);

    public TiantianResult insertContent(TbContent content);
}
