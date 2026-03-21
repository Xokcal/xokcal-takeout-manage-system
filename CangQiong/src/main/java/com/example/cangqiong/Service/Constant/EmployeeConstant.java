package com.example.cangqiong.Service.Constant;

public class EmployeeConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static final String EMPLOYEE_LOGIN_PARAM_ERROR = "员工登录，参数错误！！";
    public static final String EMPLOYEE_LOGIN_RESULT_ERROR = "员工登录，结果错误！！";
    public static final String ADD_EMPLOYEE_PARAM_ERROR = "新增员工，参数错误！！";
    public static final Integer ADD_EMPLOYEE_RESULT_RIGHT = 1;
    public static final Integer ADD_EMPLOYEE_RESULT_ERROR = 2;
    public static final String SELECT_PAGE_PARAM_ERROR = "分页查询员工，参数错误！！";
    public static final String SELECT_PAGE_RESULT_ERROR = "分页查询员工，结果错误！！";
    public static final String SELECT_TOTAL_ERROR = "查询员工总数，结果错误！！";
    public static final String QUERY_EMPLOYEE_BY_ID_PARAM_ERROR = "根据id查询员工，参数错误！！";
    public static final String QUERY_EMPLOYEE_BY_ID_RESULT_ERROR = "根据id查询员工，结果错误！！";
    public static final String UPDATE_EMPOYEE_PARAM_ERROR = "修改员工，参数错误！！";
    public static final Integer UPDATE_EMPOYEE_RESULT_RIGHT = 1;
    public static final Integer UPDATE_EMPOYEE_RESULT_ERROR = 0;
    public static final String QUERY_OLD_PASSWORD_PARAM_ERROR = "得到原始密码，参数错误！！";
    public static final String QUERY_OLD_PASSWORD_RESULT_ERROR = "得到原始密码，结果错误！！";
    public static final String UPDATE_PASSWORD_PARAM_ERROR = "修改密码，参数错误！！";
    public static final String UPDATE_PASSWORD_MATCH_ERROR = "修改密码，新旧密码匹对错误！！";
    public static final String UPDATE_EMPLOYEE_STATUS_PARAM_ERROR = "修改员工状态，参数错误！！";

    //分页查询
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }
}
