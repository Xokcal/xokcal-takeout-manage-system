package com.example.cangqiong.Service.Constant;

public class RedisCategoryConstant {
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static final String REDIS_PUT_CATEGORY_PAGE_RESULT_SUCCESS = "redis得到分类分页，储存数据成功{}";
    public static final String REDIS_GET_CATEGORY_PAGE_RESULT_SUCCESS = "从数据库查询分类分页数据，查询成功！！";
    public static final String REDIS_GET_CATEGORY_PAGE_RESULT_ERROR = "从数据库查询分类分页数据，查询数据失败成功！！";
    public static final String REDIS_DELETE_ALL_CATEGORY_PAGE_SUCCESS = "删除所有分类缓存，成功！！";
    public static final String REDIS_DELETE_ALL_CATEGORY_KEY_ERROR = "删除所有分类缓存，数据为空";
}
