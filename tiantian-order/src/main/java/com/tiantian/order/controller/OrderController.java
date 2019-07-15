package com.tiantian.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.order.pojo.OrderInfo;
import com.tiantian.order.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    @ResponseBody
    public TiantianResult createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            TiantianResult result = orderService.createOrder(orderInfo);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
