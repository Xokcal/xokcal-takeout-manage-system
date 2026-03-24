package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Report.*;

public interface ReportService {
    public RepoetUserStatisticVO getRepoetUserStatistic(ReportTimeRequestBody reportTimeRequestBody);
    public ReportTurnoverStatisticsVO getTurnoverStatistics(ReportTimeRequestBody reportTimeRequestBody);
    public ReportTop10VO getTpo10Statistic(ReportTimeRequestBody reportTimeRequestBody);
    public ReportOrdersStatisticsVO getOrderStatistic(ReportTimeRequestBody reportTimeRequestBody);
}
