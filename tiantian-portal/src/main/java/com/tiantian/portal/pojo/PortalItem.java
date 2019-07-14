package com.tiantian.portal.pojo;

import com.tiantian.pojo.TbItem;

public class PortalItem extends TbItem {
/**
 * jsp需要Item的属性里有images
 * TbItem满足不了
 * 所以写个专属于portal的继承类
 */
    
    public String[] getImages(){
        String images = this.getImage();
        if(images != null && !images.equals("")){
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;  
    }
}
