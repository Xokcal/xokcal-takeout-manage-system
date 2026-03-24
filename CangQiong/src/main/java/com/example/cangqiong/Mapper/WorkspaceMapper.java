package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Workspace.WorkspaceBusinessVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

@Mapper
public interface WorkspaceMapper {

    //获得每日运营报表数据
    @Select("select * from business_report")
    WorkspaceBusinessVO getBusinessReport();

    //获得营业额总数
    @Select("select sum(amount) from orders where status = 5 and checkout_time > date(now()) and checkout_time < now();")
    BigDecimal getTotalAmount();

    //获得今日新增用户总数
    @Select("select count(*) from user where create_time > date(now()) and create_time < now()")
    Integer getTotalNewUser();

    //接单成功率
    @Select("select (select count(*) from orders where  status = 5 and checkout_time > date (NOW()) and checkout_time <= now()) / \n" +
            "    ((select count(*) from orders where order_time > date(now()) and order_time <= now())) as completeAccuracy")
    BigDecimal getCompleteAccuracy();

    //今日平均单价
    @Select("select avg(amount) from orders where status = 5 and checkout_time > date(now()) and checkout_time < now()")
    BigDecimal getAvgAmount();

    //今日有效订单数
    @Select("select count(*) from orders where status in (2,3,4,5) and order_time > date(now()) and order_time <= now()")
    Integer getValidOrder();

    //套餐已经停售
    @Select("select count(*) from setmeal where status = 0")
    Integer getSetmealStop();

    //套餐正在起售
    @Select("select count(*) from setmeal where status = 1")
    Integer getSetmealRun();

    //已经停售菜品
    @Select("select count(*) from dish where status = 0")
    Integer getDishStop();

    //正在启售菜品
    @Select("select count(*) from dish where status = 1")
    Integer getDishRun();

    //所有订单
    @Select("select count(*) from orders where date(order_time) = date(now())")
    Integer getOrdersTotal();

    //已经取消订单
    @Select("select count(*) from orders where status = 6 and date(order_time) = date(now())")
    Integer getOrderCancel();

    //已完成数量
    @Select("select count(*) from orders where status = 5 and date(order_time) = date(now())")
    Integer getOrderFinish();

    //待派送订单
    @Select("select count(*) from orders where status = 4 and date(order_time) = date(now())")
    Integer getOrderWillDelivery();

    //待接单数量
    @Select("select count(*) from orders where status = 2 and date(order_time) = date(now())")
    Integer getOrderWillConfirm();
}
