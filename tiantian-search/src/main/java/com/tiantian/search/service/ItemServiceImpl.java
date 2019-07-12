package com.tiantian.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.search.mapper.ItemMapper;
import com.tiantian.search.pojo.SearchItem;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SolrServer solrServer;
    
    @Autowired
    private ItemMapper itemMapper;
    
    @Override
    public TiantianResult importItems() throws Exception{
        //查询数据库获得商品列表
        List<SearchItem> list = itemMapper.getItemList();
        //遍历列表
        for (SearchItem item : list) {
            //2.添加文档
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", item.getId());
            doc.addField("item_title", item.getTitle());
            doc.addField("item_sell_point", item.getSell_point());
            doc.addField("item_price", item.getPrice());
            doc.addField("item_image", item.getImage());
            doc.addField("item_category_name", item.getCategory_name());
            doc.addField("item_desc", item.getItem_desc());
            
            //3.添加到索引库
            solrServer.add(doc);
        }
        //4.提交
        solrServer.commit();
        return TiantianResult.ok();
    }


}
