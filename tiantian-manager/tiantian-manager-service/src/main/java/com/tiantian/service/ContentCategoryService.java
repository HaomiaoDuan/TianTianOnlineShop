package com.tiantian.service;

import java.util.List;

import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.common.pojo.TiantianResult;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(Long parentId);
    
    public TiantianResult insertCatgory(Long parentId, String name);
}
