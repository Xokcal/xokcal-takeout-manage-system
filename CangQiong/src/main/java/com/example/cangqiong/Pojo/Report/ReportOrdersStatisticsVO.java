package com.example.cangqiong.Pojo.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportOrdersStatisticsVO {
    private String dateList;
    private BigDecimal orderCompletionRate;
    private String orderCountList;
    private Integer totalOrderCount;
    private Integer validOrderCount;
    private String validOrderCountList;
}
