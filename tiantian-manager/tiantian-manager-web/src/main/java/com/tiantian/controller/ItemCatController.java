package com.tiantian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.service.ItemCatService;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService service;
    
    @RequestMapping("/rest/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0") Long parentId){
        List<EasyUITreeNode> itemCatList = service.getItemCatList(parentId);
        return itemCatList;
    }
    
}
