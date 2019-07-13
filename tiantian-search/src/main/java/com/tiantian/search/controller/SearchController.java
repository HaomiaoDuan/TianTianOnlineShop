package com.tiantian.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.search.pojo.SearchResult;
import com.tiantian.search.service.SearchService;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public TiantianResult search(@RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        //接收参数的判断和默认值。
        try {
            // 转换字符集
            keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
            SearchResult searchResult = searchService.search(keyword, page, rows);
            return TiantianResult.ok(searchResult);
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }

}
