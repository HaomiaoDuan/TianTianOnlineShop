package com.tiantian.portal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.portal.service.ContentService;

@Controller
public class IndexController {
    
    @Autowired
    private ContentService contentService;
    
    @RequestMapping("/index")
    public String showIndex(Model model){
        String ad1List = contentService.getAd1List();
        model.addAttribute("ad1",ad1List);
        return "index";
    }
    
    //发送post请求带有参数
    /*@RequestMapping(value = "/posttest",method=RequestMethod.POST)
    @ResponseBody
    public String postTest(String name, String pass){
        System.out.println(name);
        System.out.println(pass);
        return "OK";
    }*/
    
    //post请求中带有json时，需要用pojo/Map + @ResponseBody来接收
    @RequestMapping(value = "/posttest",method=RequestMethod.POST)
    @ResponseBody
    public String postTest(@RequestBody Map map){
        System.out.println(map.get("name"));
        System.out.println(map.get("pass"));
        return "OK";
    }
}
