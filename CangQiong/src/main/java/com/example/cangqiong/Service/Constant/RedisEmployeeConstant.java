package com.example.cangqiong.Service.Constant;

public class RedisEmployeeConstant {
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;

    public static final String REDIS_EMPLOYEE_PAGE_PUT_RESULT_ERROR = "reids储存员工页数，缓存错误，mapper为空";
    public static final String REDIS_GET_EMPLOYEE_PAGE_RESULT_ERROR = "redis得到员工分页，结果错误！！";
    public static final String REDIS_GET_EMPLOYEE_PAGE_RESULT_SUCCESS = "reids储存员工分页，获得数据成功{}";
    public static final String REDIS_PUT_EMPLOYEE_PAGE_RESULT_SUCCESS = "redis得到员工分页，储存数据成功{}";
    public static final String REDIS_EMPLOYEE_DELETE_ALL_KEY_SUCCESS = "删除所有员工缓存，删除成功！！";
    public static final String REDIS_EMPLOYEE_DELETE_ALL_KEY_ERROR = "删除所有员工缓存，key集合可能为空！！";
}
