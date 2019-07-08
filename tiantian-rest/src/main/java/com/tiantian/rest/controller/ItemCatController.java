package com.tiantian.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.rest.pojo.ItemCatResult;
import com.tiantian.rest.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")      //charset不能又空格
    @ResponseBody
    public String getItemCatList(String callback){
        ItemCatResult result = itemCatService.getItemCatResutl();
        //转为json
        String json = JsonUtils.objectToJson(result);
        //如果回调为空
        if(StringUtils.isEmpty(callback)){
            return json;
        }else{
            //非空，需要支持jsonp调用
            return callback + "(" + json + ")";
        }
    }
}
