package com.example.cangqiong.Pojo.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageResponseBody {
    private Long total;
    private List<OrderBody> records;
}
