package com.tiantian.portal.service;

import com.tiantian.pojo.TbItem;

public interface ItemService {

    public TbItem getItemById(Long itemId);
    
    public String getItemDescById(Long itemId);
    
    public String getItemParamById(Long itemId);
}
