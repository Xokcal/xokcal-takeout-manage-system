package com.example.cangqiong.Pojo.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportTop10VO {
    private String nameList;
    private String numberList;
}
