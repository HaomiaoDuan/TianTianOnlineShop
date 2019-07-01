package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.mapper.TbItemMapper;
import com.tiantian.pojo.TbItem;

/**
 * 商品管理
 * @author 89371
 *
 */
@Controller
public class ItemController {

    @Autowired
    private TbItemMapper mapper;
    
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId){
        return mapper.selectByPrimaryKey(itemId);
    }
    
}
