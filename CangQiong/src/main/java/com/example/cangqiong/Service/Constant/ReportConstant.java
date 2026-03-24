package com.example.cangqiong.Service.Constant;

public class ReportConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final String REPORT_USER_RESULT_ERROR = "用户统计接口，结果错误！！";
    public static final String USER_REPORT_PARAM_ERROR = "用户统计接口，参数错误！！";
    public static final String TURNOVER_REPORT_PARAM_ERROR = "用户统计接口，参数错误！！";
    public static final String TURNOVER_REPORT_RESULT_ERROR = "用户统计接口，结果错误！！";
    public static final String TOP10_REPORT_PARAM_ERROR = "top10数据，参数错误！！";
    public static final String TOP10_REPORT_RESULT_ERROR = "top10数据，结果错误！！";
    public static final String GET_ORDER_REPORT_PARAM_ERROR = "订单统计接口，参数错误！！";
    public static final String GET_ORDER_REPORT_RESULT_ERROR = "订单统计接口，结果错误！！";
}
