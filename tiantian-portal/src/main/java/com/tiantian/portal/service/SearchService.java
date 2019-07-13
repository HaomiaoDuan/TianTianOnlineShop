package com.tiantian.portal.service;

import com.tiantian.portal.pojo.SearchResult;

public interface SearchService {

    public SearchResult search(String keyword, int page, int rows);
}
