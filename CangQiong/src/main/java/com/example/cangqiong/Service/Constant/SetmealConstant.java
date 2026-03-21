package com.example.cangqiong.Service.Constant;

public class SetmealConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final Integer END_RIGHT_RESULT_CODE = 1;

    public static final String SETMEAL_SELECT_PARAM_ERROR = "分页查询套餐，参数错误！！";
    public static final String SETMEAL_SELECT_RESULT_ERROR = "分页查询套餐，结果错误！！";
    public static final String GET_SETMEAL_TOTAL_ERROR = "获取套餐总数失败！！";
    public static final String ADD_SETMEAL_PARAM_ERROR = "新增套餐，参数错误！！";
    public static final String ADD_SETMEAL_RESULT_DEFEAT = "新增套餐，新增Setmeal表或SetmealDish失败！！";
    public static final Integer ADD_SETMEAL_PASS_RESULT = 1;
    public static final String UPDATE_SETMEAL_PARAM_ERROR = "修改套餐，参数错误！！";
    public static final String UPDATE_SETMEALRESULT_ERROR = "修改套餐，数据库修改错误！！";
    public static final String SETMEAL_VALUE_KEY_PARAM_ERROR = "批量赋值主键，参数错误！！";
    public static final String DELETE_SETMEAL_DISH_PARAM_ERROR = "删除setmeal_dish，参数错误！！";
    public static final String DELETE_SETMEAL_DISH_RESULT_ERROR = "删除setmeal_dish，删除失败！！";
    public static final String DELETE_SETMEAL_AND_DISH_PARRAM_ERROR = "批量删除套餐，参数错误！！";
    public static final String DELETE_SETMEAL_AND_DISH_RESULT_ERROR = "批量删除套餐，结果错误！！";
    public static final String DELETE_SETMEAL_AND_DISH_RESULT_RIGHT = "批量删除套餐，删除套餐{}个，删除菜品{}个！！";
    public static final String UPDATE_SETMEAL_STATUS_PARAM_ERROR = "修改套餐状态，参数错误！！";
    public static final String SELECT_SETMEAL_BY_ID_PARAM_ERROR = "根据id查询套餐，参数错误！！";
    public static final String SELECT_SETMEAL_BY_ID_RESULT_ERROR = "根据id查询套餐，结果错误！！";
}
