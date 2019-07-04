package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.mapper.TbItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.service.ItemService;

/**
 * 商品管理
 * @author 89371
 *
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService service;
    
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId){
        return service.getItemById(itemId);
    }
    
    @RequestMapping("/rest/item")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows ){
        EasyUIDataGridResult result = service.getItemList(page, rows);
        return result;
    }
    
    
}
