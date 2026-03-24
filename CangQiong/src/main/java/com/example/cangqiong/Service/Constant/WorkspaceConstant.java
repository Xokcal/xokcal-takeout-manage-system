package com.example.cangqiong.Service.Constant;

public class WorkspaceConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final String GET_REPORT_BUSINESS_RESULT_ERROR = "得到每日运用报表数据，结果错误";

    public static final String GET_DAILY_MANAGE_DATE_RESULT_ERROR = "得到每日运用报表数据，结果错误！！";
    public static final String GET_SETMEAL_VIEW_RESULT_ERROR = "查询套餐总览，结果错误！！";
    public static final String GET_DISH_VIEW_RESULT_ERROR = "菜品总览，结果错误！！";
    public static final String GET_ORDER_VIEW_RESULT_ERROR = "查询订单管理数据，参数错误！！";
}
