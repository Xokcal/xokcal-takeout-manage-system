package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Controller.OrderController;
import com.example.cangqiong.Mapper.OrderMapper;
import com.example.cangqiong.Pojo.Order.*;
import com.example.cangqiong.Service.Constant.OrderConstant;
import com.example.cangqiong.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    //查询订单
    @Override
    public OrderBody selectOrderById(long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(OrderConstant.QUERY_ORDER_PARAM_ERROR);
            throw new BusinessException(OrderConstant.QUERY_ORDER_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        OrderBody orderBody = orderMapper.selectOrderById(id);
        List<OrderDetailBody> orderDetailBodies = orderMapper.selectOrderDetailById(id);
        if (!CheckIsValidUtil.isValid(orderDetailBodies) || !CheckIsValidUtil.isValid(orderBody)) {
            log.warn(OrderConstant.QUERY_ORDER_RESULT_ERROR);
            throw new BusinessException(OrderConstant.QUERY_ORDER_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        orderBody.setOrderDetailList(orderDetailBodies);
        return orderBody;
    }

    //分页查询订单
    @Override
    public OrderPageResponseBody pageOrder(OrderPageRequestBody orderPageRequestBody) {
        if (!CheckIsValidUtil.isValid(orderPageRequestBody)) {
            log.warn(OrderConstant.PAGE_ORDER_PARAM_ERROR);
            throw new BusinessException(OrderConstant.PAGE_ORDER_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer start = OrderConstant.startPage(orderPageRequestBody.getPage(), orderPageRequestBody.getPageSize());
        List<OrderBody> orderBodies = orderMapper.pageOrder(orderPageRequestBody, start);
        if (!CheckIsValidUtil.isValid(orderBodies)) {
            log.warn(OrderConstant.PAGE_ORDER_RESULT_ERROR);
            throw new BusinessException(OrderConstant.PAGE_ORDER_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        orderBodies.forEach(e -> {
            e.setOrderDishes(e.getNumber() + "+" + e.getAddress());
        });
        return new OrderPageResponseBody(selectOrderTotal(), orderBodies);
    }

    //接单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer orderConfirm(OrderConfirmRequestBody orderConfirmRequestBody) { // 2 -> 3
        if (!CheckIsValidUtil.isValid(orderConfirmRequestBody)) {
            log.warn(OrderConstant.ORDER_CONFIRM_PARAM_ERROR);
            throw new BusinessException(OrderConstant.ORDER_CONFIRM_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer row = orderMapper.updateOrderStatusConfirm(OrderConstant.ORDER_CONFIRM_CONVERT_CODE
                , orderConfirmRequestBody.getId());
        return row;
    }

    //派送订单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deliveryOrder(long id) { // 3 -> 4
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(OrderConstant.DELIVERY_ORDER_PARAM_ERROR);
            throw new BusinessException(OrderConstant.DELIVERY_ORDER_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer row = orderMapper.updateOrderStatusDelivery(OrderConstant
                .ORDER_DELIVERY_CONVERT_CODE, LocalDateTime.now(), id);
        if (row < 1) {
            log.warn(OrderConstant.DELIVERY_RESULT_ERROR);
            throw new BusinessException(OrderConstant.DELIVERY_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        return row;
    }

    //拒单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer orderRejection(OrderRejectionRequestBody orderRejectionRequestBody) {
        if (!CheckIsValidUtil.isValid(orderRejectionRequestBody)) {
            log.warn(OrderConstant.REJECTION_PARAM_ERROR);
            throw new BusinessException(OrderConstant.REJECTION_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer row = orderMapper.updateOrderRejection(OrderConstant.REJECTION_STATUS_CODE,
                orderRejectionRequestBody.getRejectionReason(), orderRejectionRequestBody.getId()
                , LocalDateTime.now());
        if (row < 1) {
            log.warn(OrderConstant.REJECTION_RESULT_ERROR);
            throw new BusinessException(OrderConstant.REJECTION_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        return row;
    }

    //取消
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer orderCancel(OrderCancelRequestBody orderCancelRequestBody) {
        if (!CheckIsValidUtil.isValid(orderCancelRequestBody)) {
            log.warn(OrderConstant.ORDER_CANCEL_PARAM_ERROR);
            throw new BusinessException(OrderConstant.ORDER_CANCEL_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer row = orderMapper.updateOrderCancel(OrderConstant.ORDER_CANCEL_STATUS_CODE
                , orderCancelRequestBody.getCancelReason(), LocalDateTime.now()
                , orderCancelRequestBody.getId());
        if (row < 1) {
            log.warn(OrderConstant.ORDER_CANCEL_RESULT_ERROR);
            throw new BusinessException(OrderConstant.ORDER_CANCEL_RESULT_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        return row;
    }

    //订单统计
    @Override
    public OrderStatisticsBody orderStatistics() {
        List<Map<Object, Object>> orderStatisticsMap = orderMapper.getOrderStatistics();
        if (!CheckIsValidUtil.isValid(orderStatisticsMap)) {
            log.warn(OrderConstant.STATISTICS_PARAM_ERROR);
            throw new BusinessException(OrderConstant.STATISTICS_PARAM_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        OrderStatisticsBody orderStatisticsBody = new OrderStatisticsBody(0, 0, 0, 0);
        for (Map<Object, Object> map : orderStatisticsMap) {
            String statisticsCode = map.get("statistic").toString();
            Integer sum = Integer.valueOf(String.valueOf(map.get("sum")));
            switch (statisticsCode) {
                case "toBeConfirmed":
                    orderStatisticsBody.setToBeConfirmed(sum);
                    break;
                case "confirmed":
                    orderStatisticsBody.setConfirmed(sum);
                    break;
                case "deliveryInProgress":
                    orderStatisticsBody.setDeliveryInProgress(sum);
                    break;
                case "other":
                    orderStatisticsBody.setOther(sum);
                    break;
            }
        }
        if (!CheckIsValidUtil.isValid(orderStatisticsBody)) {
            log.warn(OrderConstant.STATISTICS_PARAM_ERROR);
            throw new BusinessException(OrderConstant.STATISTICS_PARAM_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        return orderStatisticsBody;
    }

    //完成订单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer orderComplete(long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(OrderConstant.COMPLETE_PARAM_ERROR);
            throw new BusinessException(OrderConstant.COMPLETE_PARAM_ERROR
                    , OrderConstant.CODE_FRONT);
        }
        Integer row = orderMapper.updateOrderComplete(OrderConstant.COMPLETE_STATUS_CODE, id);
        if (row < 1) {
            log.warn(OrderConstant.COMPLETE_RESULT_ERROR);
            throw new BusinessException(OrderConstant.COMPLETE_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        return row;
    }

    //查询订单总数
    private long selectOrderTotal() {
        Long total = orderMapper.selectOrderTotal();
        if (!CheckIsValidUtil.isValid(total)) {
            log.warn(OrderConstant.QUERY_ORDER_TOTAL_RESULT_ERROR);
            throw new BusinessException(OrderConstant.QUERY_ORDER_TOTAL_RESULT_ERROR
                    , OrderConstant.CODE_BEHIND);
        }
        return total;
    }


}
