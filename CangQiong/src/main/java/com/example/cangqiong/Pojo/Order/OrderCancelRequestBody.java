package com.example.cangqiong.Pojo.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCancelRequestBody {
    private Long id;
    private String cancelReason;
}
