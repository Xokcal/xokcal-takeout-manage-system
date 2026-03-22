package com.example.cangqiong.Pojo.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailBody {
    private Long id;
    private Long orderId;
    private Long dishId;
    private Long setmealId;
    private String name;
    private Integer number;
    private Double amount;
    private String dishFlavor;
    private String image;
}
