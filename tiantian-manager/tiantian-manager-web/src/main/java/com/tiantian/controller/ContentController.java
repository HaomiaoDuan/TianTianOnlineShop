package com.tiantian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.EasyUIDataGridResult;
import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.HttpClientUtil;
import com.tiantian.pojo.TbContent;
import com.tiantian.service.ContentService;


@Controller
@RequestMapping("/rest/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    
    //Controller是springmvc在管理，spring是父容器其中配置了扫描properties文件的功能
    //所以在父容器管理的service类中，可以用@value获取properties文件的属性
    //而Controller无法获取
    //所以应该在springmvc.xml中加上扫描配置文件的功能。
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;
    
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Integer page,Integer rows,Long categoryId){
        EasyUIDataGridResult result = contentService.getContentListByCatId(page, rows,categoryId);
        return result;
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public TiantianResult insertContent(TbContent content) {
        
        TiantianResult result = contentService.insertContent(content);
        //调用rest发布的服务来同步缓存
        System.out.println(REST_BASE_URL + REST_CONTENT_SYNC_URL+content.getCategoryId());
        HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL+content.getCategoryId());
       
        return result;
    }

}
