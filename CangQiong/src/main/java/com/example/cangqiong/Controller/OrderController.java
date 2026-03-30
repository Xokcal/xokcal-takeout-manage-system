package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Pojo.Order.*;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Service.Implement.OrderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class  OrderController {

    @Autowired
    private OrderImpl orderImpl;

    //根据id查询订单
    @GetMapping("/order/details/{id}")
    @Operation(summary = "根据id查询" , description = "根据id查询订单")
    R getOrderById(@PathVariable long id){
        OrderBody r = orderImpl.selectOrderById(id);
        return new R().ok(r);
    }

    //分页查询订单
    @GetMapping("/order/conditionSearch")
    @Operation(summary = "分页查询" , description = "分页查询订单")
    R pageOrder(OrderPageRequestBody orderPageRequestBody){
        OrderPageResponseBody r = orderImpl.pageOrder(orderPageRequestBody);
        return new R().ok(r);
    }

    //派送订单
    @PutMapping("/order/delivery/{id}")
    @Operation(summary = "修改" , description = "派送订单")
    R deliveryOrder(@PathVariable long id){
        Integer r = orderImpl.deliveryOrder(id);
        return new R().ok(r);
    }

    //接单
    @PutMapping("/order/confirm")
    @Operation(summary = "修改" , description = "接单")
    R orderConfirm(@RequestBody OrderConfirmRequestBody orderConfirmRequestBody){
        Integer r = orderImpl.orderConfirm(orderConfirmRequestBody);
        return new R().ok(r);
    }

    //拒绝接单
    @PutMapping("/order/rejection")
    @Operation(summary = "修改" , description = "拒绝接单")
    R orderRejection(@RequestBody OrderRejectionRequestBody orderRejectionRequestBody){
        Integer r = orderImpl.orderRejection(orderRejectionRequestBody);
        return new R().ok(r);
    }

    //完成订单
    @PutMapping("/order/complete/{id}")
    @Operation(summary = "修改" , description = "完成订单")
    R orderComplete(@PathVariable long id){
        Integer r = orderImpl.orderComplete(id);
        return new R().ok(r);
    }

    //取消订单
    @PutMapping("/order/cancel")
    @Operation(summary = "修改" , description = "取消订单")
    R orderCancel(@RequestBody OrderCancelRequestBody orderCancelRequestBody){
        Integer r = orderImpl.orderCancel(orderCancelRequestBody);
        return new R().ok(r);
    }

    //订单统计
    @GetMapping("/order/statistics")
    @Operation(summary = "查询" , description = "查询订单统计，获得每个状态数量")
    R getOrderStatistics(){
        OrderStatisticsBody r = orderImpl.orderStatistics();
        return new R().ok(r);
    }

}

