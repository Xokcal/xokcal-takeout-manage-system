package com.example.cangqiong.Service.Constant;

public class ShopConstant {
    public static final int CODE_FRONT = 400;
    public static final int CODE_BEHIND = 500;
    public static Integer startPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static final String UPDATE_SHOP_STATUS_PAARAM_ERROR = "修改营业状态，参数错误！！";
}
