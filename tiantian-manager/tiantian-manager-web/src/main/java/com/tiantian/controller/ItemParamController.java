package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {


    @Autowired
    private ItemParamService itemParamService;
    
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page,Integer rows ){
        EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }
    
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TiantianResult getItemCatByCid(@PathVariable Long cid){
        TiantianResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }
    
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TiantianResult insertItemParam(@PathVariable Long cid, String paramData) {
        TiantianResult result = itemParamService.insertItemParam(cid, paramData);
        return result;
    }
    
}
