package com.tiantian.search.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.search.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/importall")
    @ResponseBody
    public TiantianResult importAll(){
        try {
            return itemService.importItems();
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtils.getStackTrace(e));
        }
    }
}
