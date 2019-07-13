package com.tiantian.portal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.HttpClientUtil;
import com.tiantian.portal.pojo.SearchResult;

@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) {
            //调用服务查询商品列表
            Map<String, String> param = new HashMap<>();
            param.put("keyword", keyword);
            param.put("page", page + "");
            param.put("rows", rows + "");
            //调用服务
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);//Get的查询方法
            //转换成java对象
            TiantianResult taotaoResult = TiantianResult.formatToPojo(json, SearchResult.class);
            //取返回的结果
            SearchResult searchResult = (SearchResult) taotaoResult.getData();
            
            return searchResult;
    }

}

