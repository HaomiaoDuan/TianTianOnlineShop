package com.tiantian.service;

import java.util.List;

import com.tiantian.common.pojo.EasyUITreeNode;

public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(long parentId);
}
