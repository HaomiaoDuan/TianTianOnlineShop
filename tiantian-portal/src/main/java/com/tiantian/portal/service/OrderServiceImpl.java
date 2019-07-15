package com.tiantian.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.HttpClientUtil;
import com.tiantian.common.utils.JsonUtils;
import com.tiantian.portal.pojo.OrderInfo;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;

    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(OrderInfo orderInfo) {
        // 把OrderInfo转换成json
        String json = JsonUtils.objectToJson(orderInfo);
        // 提交订单数据
        String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
        // 转换成java对象
        TiantianResult taotaoResult = TiantianResult.format(jsonResult);
        // 取订单号
        String orderId = taotaoResult.getData().toString();
        return orderId;
    }

}
