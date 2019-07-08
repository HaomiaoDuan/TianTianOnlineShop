package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.tiantian.common.pojo.PictureResult;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.service.PictureService;

@Controller
public class PictureController {

    @Autowired
    private PictureService service;
    
    @RequestMapping("/rest/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        
        PictureResult result = service.uploadPic(uploadFile);
        //解决浏览器的兼容性
        String json = JsonUtils.objectToJson(result);
        return json;
    }
    
}
