package com.tiantian.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.HttpClientUtil;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.pojo.TbContent;
import com.tiantian.portal.pojo.AdNode;

@Service
public class ContentServiceImpl implements ContentService {

    //直接调用rest的服务
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    
    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;
    
    /**
     * 获得大广告位的内容 cid=89
     *
     */
    @Override
    public String getAd1List() {
        //调用服务获得json数据
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        //把json转换成java对象
        TiantianResult tiantianResult = TiantianResult.formatToList(json, TbContent.class);
        //取data属性，内容列表
        List<TbContent> list = (List<TbContent>)tiantianResult.getData();
        //转成List<AdNode>
        List<AdNode> resultList = new ArrayList<>() ;
        for (TbContent tbContent : list) {
            AdNode node = new AdNode();
            node.setHeight(240);        //手动设置
            node.setWidth(670);
            node.setSrc(tbContent.getPic());    //第一幅图
            
            node.setHeightB(240);
            node.setWidthB(550);
            node.setSrcB(tbContent.getPic2());  //备用图
            
            node.setAlt(tbContent.getSubTitle());
            node.setHref(tbContent.getUrl());
            
            resultList.add(node);
        }
        //结果转成json
        String resultJson = JsonUtils.objectToJson(resultList);
        return resultJson;
    }

    
 
}
