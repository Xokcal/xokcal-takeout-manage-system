package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Pojo.Report.*;
import com.example.cangqiong.Service.Implement.ReportImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class ReportController {

    @Autowired
    private ReportImpl reportImpl;

    //导出Excel报表接口
    @GetMapping("/report/export")
    @Operation(summary = "输出" , description = "导出Excel报表接口")
    String reportExcelApi(){
        return "ok";
    }

    //查询销量排名top10接口
    @GetMapping("/report/top10")
    @Operation(summary = "查询" , description = "查询销量排名top10接口")
    R reportTop10(ReportTimeRequestBody reportTimeRequestBody){
        ReportTop10VO r = reportImpl.getTpo10Statistic(reportTimeRequestBody);
        return new R().ok(r);
    }

    //营业额统计接口
    @GetMapping("/report/turnoverStatistics")
    @Operation(summary = "查询" , description = "营业额统计接口")
    R reportTurnoverStatistics(ReportTimeRequestBody reportTimeRequestBody){
        ReportTurnoverStatisticsVO r = reportImpl.getTurnoverStatistics(reportTimeRequestBody);
        return new R().ok(r);
    }

    //用户统计接口
    @GetMapping("/report/userStatistics")
    @Operation(summary = "查询" , description = "用户统计接口")
    R reportUserStatistics(ReportTimeRequestBody reportTimeRequestBody){
        RepoetUserStatisticVO r = reportImpl.getRepoetUserStatistic(reportTimeRequestBody);
        return new R().ok(r);
    }

    //订单统计接口
    @GetMapping("/report/ordersStatistics")
    @Operation(summary = "查询" , description = "订单统计接口")
    R reportOrdersStatistics(ReportTimeRequestBody reportTimeRequestBody){
        ReportOrdersStatisticsVO r = reportImpl.getOrderStatistic(reportTimeRequestBody);
        return new R().ok(r);
    }

}
