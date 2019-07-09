package com.tiantian.rest.service;

import java.util.List;

import com.tiantian.pojo.TbContent;

public interface ContentService {

    List<TbContent> getContentList(Long cid);
}
