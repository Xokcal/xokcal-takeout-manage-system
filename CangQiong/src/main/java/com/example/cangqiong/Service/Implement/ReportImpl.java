package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.StringUtil.StringUtil;
import com.example.cangqiong.Mapper.ReportMapper;
import com.example.cangqiong.Pojo.Report.*;
import com.example.cangqiong.Service.Constant.ReportConstant;
import com.example.cangqiong.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReportImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    //用户统计接口
    @Override
    public RepoetUserStatisticVO getRepoetUserStatistic(ReportTimeRequestBody reportTimeRequestBody) {
        if (!CheckIsValidUtil.isValid(reportTimeRequestBody)){
            log.warn(ReportConstant.USER_REPORT_PARAM_ERROR);
            throw new BusinessException(ReportConstant.USER_REPORT_PARAM_ERROR
                    ,ReportConstant.CODE_FRONT);
        }
        RepoetUserStatisticVO repoetUserStatisticVO = new RepoetUserStatisticVO().builder()
                .dateList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.userAllDate(reportTimeRequestBody)))
                .newUserList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.userEveryDayNew(reportTimeRequestBody)))
                .totalUserList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.userEveryDayCount(reportTimeRequestBody))).build();
        if (!CheckIsValidUtil.isValid(repoetUserStatisticVO)){
            log.warn(ReportConstant.REPORT_USER_RESULT_ERROR);
            throw new BusinessException(ReportConstant.REPORT_USER_RESULT_ERROR
                    ,ReportConstant.CODE_BEHIND);
        }
        return repoetUserStatisticVO;
    }

    //
    @Override
    public ReportTurnoverStatisticsVO getTurnoverStatistics(ReportTimeRequestBody reportTimeRequestBody){
        if (!CheckIsValidUtil.isValid(reportTimeRequestBody)){
            log.warn(ReportConstant.TURNOVER_REPORT_PARAM_ERROR);
            throw new BusinessException(ReportConstant.TURNOVER_REPORT_PARAM_ERROR
                    ,ReportConstant.CODE_FRONT);
        }
        ReportTurnoverStatisticsVO reportTurnoverStatisticsVO = new ReportTurnoverStatisticsVO().builder()
                .dateList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.turnoverAllDate(reportTimeRequestBody)))
                .turnoverList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.turnoverEveryDayMargin(reportTimeRequestBody)))
                .build();
        if (!CheckIsValidUtil.isValid(reportTurnoverStatisticsVO)){
            log.warn(ReportConstant.TURNOVER_REPORT_RESULT_ERROR);
            throw new BusinessException(ReportConstant.TURNOVER_REPORT_RESULT_ERROR
                    ,ReportConstant.CODE_BEHIND);
        }
        return reportTurnoverStatisticsVO;
    }

    //top10数据
    @Override
    public ReportTop10VO getTpo10Statistic(ReportTimeRequestBody reportTimeRequestBody){
        if (!CheckIsValidUtil.isValid(reportTimeRequestBody)){
            log.warn(ReportConstant.TOP10_REPORT_PARAM_ERROR);
            throw new BusinessException(ReportConstant.TOP10_REPORT_PARAM_ERROR
                    ,ReportConstant.CODE_FRONT);
        }
        ReportTop10VO reportTop10VO = new ReportTop10VO().builder()
                .nameList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.top10Names(reportTimeRequestBody)))
                .numberList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.top10Numbers(reportTimeRequestBody)))
                .build();
        if (!CheckIsValidUtil.isValid(reportTop10VO)){
            log.warn(ReportConstant.TOP10_REPORT_RESULT_ERROR);
            throw new BusinessException(ReportConstant.TOP10_REPORT_RESULT_ERROR
                    ,ReportConstant.CODE_BEHIND);
        }
        return reportTop10VO;
    }

    //订单统计接口
    @Override
    public ReportOrdersStatisticsVO getOrderStatistic(ReportTimeRequestBody reportTimeRequestBody){
        if (!CheckIsValidUtil.isValid(reportTimeRequestBody)){
            log.warn(ReportConstant.GET_ORDER_REPORT_PARAM_ERROR);
            throw new BusinessException(ReportConstant.GET_ORDER_REPORT_PARAM_ERROR
                    ,ReportConstant.CODE_FRONT);
        }
        ReportOrdersStatisticsVO reportOrdersStatisticsVO = new ReportOrdersStatisticsVO().builder()
                .dateList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.orderDateList(reportTimeRequestBody)))
                .orderCompletionRate(reportMapper.orderCompletionRate(reportTimeRequestBody))
                .orderCountList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.orderCountList(reportTimeRequestBody)))
                .totalOrderCount(reportMapper.orderCount(reportTimeRequestBody))
                .validOrderCount(reportMapper.orderEffecttiveOrderCount(reportTimeRequestBody))
                .validOrderCountList(StringUtil.arrElementAssembledStringSeparateIsDot
                        (reportMapper.orderEffectiveOrderList(reportTimeRequestBody)))
                .build();
        if (!CheckIsValidUtil.isValid(reportOrdersStatisticsVO)){
            log.warn(ReportConstant.GET_ORDER_REPORT_RESULT_ERROR);
            throw new BusinessException(ReportConstant.GET_ORDER_REPORT_RESULT_ERROR
                    ,ReportConstant.CODE_BEHIND);
        }
        return reportOrdersStatisticsVO;
    }



}
