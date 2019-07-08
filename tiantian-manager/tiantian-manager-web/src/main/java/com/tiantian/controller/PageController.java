package com.tiantian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 页面跳转
 * @author 89371
 *
 */

@Controller
public class PageController {
    
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    
    //类是Controller，方法是Handler
    @RequestMapping("/rest/page/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
