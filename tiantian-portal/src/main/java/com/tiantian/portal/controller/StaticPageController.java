package com.tiantian.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.portal.service.StaticPageService;

@Controller
public class StaticPageController {

    @Autowired
    private StaticPageService staticPageService;

    @RequestMapping("/gen/item/{itemId}")
    @ResponseBody
    public TiantianResult genItemPage(@PathVariable Long itemId) {
        try {
            TiantianResult result = staticPageService.genItemHtml(itemId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
