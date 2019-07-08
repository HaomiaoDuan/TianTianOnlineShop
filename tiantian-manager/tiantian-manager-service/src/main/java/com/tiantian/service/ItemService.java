package com.tiantian.service;

import java.util.List;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.pojo.TbItem;

public interface ItemService {
    
    TbItem getItemById(Long id);
    
    EasyUIDataGridResult getItemList(Integer page,Integer rows);
    
    TiantianResult createItem(TbItem item, String desc,String itemParam);
    
    public String getItemParamHtml(Long itemId);
}
