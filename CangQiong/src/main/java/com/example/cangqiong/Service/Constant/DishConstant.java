package com.example.cangqiong.Service.Constant;

public class DishConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final Integer END_RIGHT_RESULT_CODE = 1;

    public static final String GET_DISH_TOTAL_RESULT_ERROR = "获取菜品总数，结果错误！！";
    public static final String SELECT_DISH_PAGE_PARAM_ERROR = "分页查询菜品，参数错误！！";
    public static final String SELECT_DISH_PAGE_RESULT_ERROR = "分页查询菜品，结果错误！！";
    public static final String ADD_DISH_PARAM_ERROR = "新增菜品，参数错误！！";
    public static final String ADD_DISH_RESULT_ERROR = "新增菜品，插入错误！！";
    public static final String ADD_DISH_RESULT_RIGHT = "新增菜品，插入Dish{}个，Flavor{}个！！";
    public static final String DISH_KEY_TO_FLAVOR_PARAM_ERROR = "将dish的主键给到flavor，参数错误！！";
    public static final String UPDATE_DISH_PARAM_ERROR = "更新菜品，参数错误！！";
    public static final String UPDATE_DISH_DELETE_FLAVOR_ERROR = "更新菜品，删除风味或新增风味错误！！";
    public static final String DELETE_DISH_AND_FLAVOR_PARAM_ERROR = "批量删除，参数错误！！";
    public static final String DELETE_DISH_AND_FLAVOR_RESULT_ERROR = "批量删除，删除错误！！";
    public static final String DELETE_DISH_RESULT_RIGHT = "批量删除成功，菜品删除{}个，风味删除{}个";
    public static final String QUERY_DISH_BY_ID_PARAM_ERROR = "根据id查询菜品，参数错误！！";
    public static final String QUERY_DISH_BY_ID_DISH_RESULT_ERROR = "根据id查询菜品，餐品查询错误错误！！";
    public static final String QUERY_DISH_BY_ID_FLAVOR_RESULT_ERROR = "根据id查询菜品，风味查询错误错误！！";
    public static final String QUERY_DISH_BY_CATEGORY_ID_PARAM_ERROR = "根据分类id查询餐品，参数错误！！";
    public static final String QUERY_DISH_BY_CATEGORY_ID_RESULT_ERROR = "根据分类id查询餐品，参数错误！！";
    public static final String UPDATE_DISH_STATUS_PARAM_ERROR = "菜品起售、停售，参数错误！！";
    public static final String UPDATE_DISH_STATUS_RESULT_ERROR = "菜品起售、停售，结果错误！！";
}
