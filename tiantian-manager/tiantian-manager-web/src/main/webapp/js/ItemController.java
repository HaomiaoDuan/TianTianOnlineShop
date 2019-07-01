package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.pojo.TbItem;
import com.tiantian.service.ItemService;

/**
 * 商品查询
 * @author 89371
 *
 */

@Controller
public class ItemController {
 
    @Autowired
    private ItemService service;
    
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable() Long itemId){
        TbItem item = service.getItemById(itemId);
        return item;
    }
    
}
