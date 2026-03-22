package com.example.cangqiong.Pojo.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatisticsBody {
    private Integer confirmed; //待派送数量
    private Integer deliveryInProgress; //派送中数量
    private Integer toBeConfirmed; //待接单数量
    private Integer other; //其他数量
}
