package com.tiantian.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemDesc;
import com.tiantian.pojo.TbItemParamItem;
import com.tiantian.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     * 
     * @param itemId
     * @return
     */
    @RequestMapping("/base/{itemId}")
    @ResponseBody
    public TiantianResult getItemById(@PathVariable Long itemId) {
        try {
            TbItem item = itemService.getItemById(itemId);
            return TiantianResult.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TiantianResult getItemDescById(@PathVariable Long itemId) {
        try {
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            return TiantianResult.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TiantianResult getItemParamById(@PathVariable Long itemId) {
         try {
             TbItemParamItem itemParamItem = itemService.getItemParamById(itemId);
             return TiantianResult.ok(itemParamItem);
         } catch (Exception e) {
             e.printStackTrace();
             return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
         }
    }
    

}
