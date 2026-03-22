package com.example.cangqiong.Pojo.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPageRequestBody {
    private String beginTime;
    private String endTime;
    private String number;
    private Integer page;
    private Integer pageSize;
    private String phone;
    private Integer status;
}
