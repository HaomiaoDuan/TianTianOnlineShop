package com.tiantian.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.portal.pojo.CartItem;

public interface CartService {

    public TiantianResult addCart(Long itemId, Integer num, HttpServletRequest request,
            HttpServletResponse response);
    
    public List<CartItem> getCartItems(HttpServletRequest request);
    
    public TiantianResult updateCartItem(long itemId, Integer num, HttpServletRequest request,
            HttpServletResponse response);
    
    public TiantianResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
