package com.tiantian.rest.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.pojo.TbContent;
import com.tiantian.rest.service.ContentService;

@Controller
public class ContentController {
    
    @Autowired
    private ContentService contentService;
    
    @RequestMapping("/content/{cid}")
    @ResponseBody
    public TiantianResult getContentList(@PathVariable Long cid){
        try {
            List<TbContent> list = contentService.getContentList(cid);
            return TiantianResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = ExceptionUtil.getStackTrace(e);
            return TiantianResult.build(500, msg);
        }
    }
}
