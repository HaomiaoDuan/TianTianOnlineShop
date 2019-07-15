package com.tiantian.order.service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.order.pojo.OrderInfo;

public interface OrderService {

    public TiantianResult createOrder(OrderInfo orderInfo);
}
