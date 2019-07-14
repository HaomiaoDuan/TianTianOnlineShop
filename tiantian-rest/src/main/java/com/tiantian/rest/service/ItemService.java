package com.tiantian.rest.service;

import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemDesc;
import com.tiantian.pojo.TbItemParamItem;

public interface ItemService {

    public TbItem getItemById(Long itemId);
    
    public TbItemDesc getItemDescById(Long itemId);
    
    public TbItemParamItem getItemParamById(Long itemId);
}
