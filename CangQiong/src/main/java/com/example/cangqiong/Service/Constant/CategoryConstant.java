package com.example.cangqiong.Service.Constant;

public class CategoryConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final String CATEGORY_PAGE_PARAM_ERROR = "分类分页查询，参数错误！！";
    public static final String CATEGORY_PAGE_RESULT_ERROR = "分类分页查询，结果错误！！";
    public static final String CATEGORY_TOTAL_RESULT_ERROR = "分类总数，结果错误！！";
    public static final String ADD_CATEGORY_PARAM_ERROR = "新增分类，参数错误！！";
    public static final String UPDATE_CATEGORY_PARAM_ERROR = "修改分类，参数错误！！";
    public static final String UPDATE_CATEGORY_STATUS_PARAM_ERROR = "修改分类状态，参数错误！！";
    public static final String DELETE_CATEGORY_PARAM_ERROR = "删除分类根据id，参数错误！！";
    public static final String SELECT_BY_TYPE_PARAM_ERROR = "根据type查询分类，参数错误！！";
    public static final String SELECT_BY_TYPE_RESULT_ERROR = "根据type查询分类，结果错误！！";

}
