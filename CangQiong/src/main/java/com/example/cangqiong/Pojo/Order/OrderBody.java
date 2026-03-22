package com.example.cangqiong.Pojo.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBody {
    private Long id;
    private String number;
    private Long userId;
    private Long addressBookId;
    private String address;
    private String consignee;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime;
    private Integer payMethod;
    private Integer payStatus;
    private Integer status;
    private Double amount;
    private Integer packAmount;
    private Integer tablewareNumber;
    private Integer tablewareStatus;
    private String remark;
    private String cancelReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelTime;
    private String rejectionReason;
    private Integer deliveryStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;
    private String estimatedDeliveryTime;
    private String userName;
    // 订单明细列表（关联明细表）
    private List<OrderDetailBody> orderDetailList;
    // 冗余字段：拼接的商品信息（可选）
    private String orderDishes;
}
