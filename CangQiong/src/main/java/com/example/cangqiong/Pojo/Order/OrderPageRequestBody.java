package com.example.cangqiong.Pojo.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
