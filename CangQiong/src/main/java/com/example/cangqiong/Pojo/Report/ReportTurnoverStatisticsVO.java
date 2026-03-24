package com.example.cangqiong.Pojo.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportTurnoverStatisticsVO {
    private String dateList;
    private String turnoverList;

}
