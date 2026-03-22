package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Order.*;

import java.util.List;

public interface OrderService {
    public OrderBody selectOrderById(long id);
    public OrderPageResponseBody pageOrder(OrderPageRequestBody orderPageRequestBody);
    public Integer deliveryOrder(long id);
    public Integer orderConfirm(OrderConfirmRequestBody orderConfirmRequestBody);
    public Integer orderRejection(OrderRejectionRequestBody orderRejectionRequestBody);
    public Integer orderComplete(long id);
    public Integer orderCancel(OrderCancelRequestBody orderCancelRequestBody);
    public OrderStatisticsBody orderStatistics();

}
