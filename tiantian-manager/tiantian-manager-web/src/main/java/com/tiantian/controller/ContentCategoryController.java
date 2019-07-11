package com.tiantian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.EasyUITreeNode;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.service.ContentCategoryService;

@Controller
@RequestMapping("/rest/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
        List<EasyUITreeNode> result = contentCategoryService.getContentCatList(parentId);
        return result;
    }
    
    @RequestMapping("/create")
    @ResponseBody
    public TiantianResult createNode(Long parentId, String name) {
        TiantianResult result = contentCategoryService.insertCatgory(parentId, name);
        return result;
    }

}
