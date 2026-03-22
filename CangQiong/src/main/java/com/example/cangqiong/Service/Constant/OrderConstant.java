package com.example.cangqiong.Service.Constant;

public class OrderConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final String QUERY_ORDER_PARAM_ERROR = "查询订单，参数错误！！";
    public static final String QUERY_ORDER_RESULT_ERROR = "查询订单，结果错误！！";
    public static final String PAGE_ORDER_PARAM_ERROR = "分页查询订单，参数错误";
    public static final String PAGE_ORDER_RESULT_ERROR = "分页查询订单，结果错误";
    public static final String QUERY_ORDER_TOTAL_RESULT_ERROR = "查询订单总数，结果错误";
    public static final String DELIVERY_ORDER_PARAM_ERROR = "派送订单，参数错误";
    public static final Integer DELIVERY_ORDER_RETURN_CODE = 1;
    public static final String ORDER_CONFIRM_PARAM_ERROR = "接单，参数错误！！！";
    public static final Integer ORDER_CONFIRM_RETURN_CODE = 1;
    public static final Integer ORDER_CONFIRM_CONVERT_CODE = 3;
    public static final Integer ORDER_DELIVERY_CONVERT_CODE = 4;
    public static final String REJECTION_PARAM_ERROR = "拒单，参数错误！！";
    public static final String REJECTION_RESULT_ERROR = "拒单，结果错误！！";
    public static final String DELIVERY_RESULT_ERROR = "派送订单，结果错误！！";
    public static final String COMPLETE_PARAM_ERROR = "完成订单，参数错误！！";
    public static final Integer COMPLETE_STATUS_CODE = 5;
    public static final String COMPLETE_RESULT_ERROR = "完成订单，结果错误！！";
    public static final Integer REJECTION_STATUS_CODE = 6;
    public static final String ORDER_CANCEL_PARAM_ERROR = "取消订单，参数错误！！";
    public static final String ORDER_CANCEL_RESULT_ERROR = "取消订单，总数错误！！";
    public static final Integer ORDER_CANCEL_STATUS_CODE = 6;
    public static final String STATISTICS_PARAM_ERROR = "订单统计，参数错误！！";
}
