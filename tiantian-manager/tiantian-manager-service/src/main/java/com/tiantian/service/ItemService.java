package com.tiantian.service;

import java.util.List;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.pojo.TbItem;

public interface ItemService {
    
    TbItem getItemById(Long id);
    
    
    EasyUIDataGridResult getItemList(Integer page,Integer rows);
    
}
