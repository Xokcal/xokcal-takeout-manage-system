package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Setmeal.PageSetmealRequetBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealAndDishBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealDishBody;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SetmealMapper {

    //分页查询套餐
    List<SetmealAndDishBody> selectSetmealPage(@Param("param") PageSetmealRequetBody pageSetmealRequetBody
            , @Param("start") Integer start);

    //获得套餐总数
    @Select("select count(*) from setmeal")
    long getSermealTotal();

    //新增套餐setmeal
    @Options(useGeneratedKeys = true , keyProperty = "id")
    @Insert("INSERT INTO setmeal (categoryId, name, price, status, description, image) " +
            "VALUES (#{categoryId}, #{name}, #{price}, #{status}, #{description}, #{image})")
    Integer insertSetmeal(SetmealAndDishBody setmealAndDishBody);

    //新增套餐dish
    Integer insertSetmealDish(@Param("dishList") List<SetmealDishBody> setmealDishBody);

    //修改setmeal
    @Update("update setmeal set categoryId = #{categoryId},description = #{description}" +
            ",image = #{image},name = #{name},price = #{price},status = #{status} where id = #{id}")
    Integer updateSetmeal(SetmealAndDishBody setmealAndDishBody);

    //批量修改setmeal_dish
    Integer updateSetmealDish(List<SetmealDishBody> list);

    //删除与setmeal关联的setmeal_dish
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    Integer deleteSetmealDish(@Param("setmealId") long setmealId);

    //批量删除setmeal
    Integer batchDeleteSetmeal(@Param("ids")List<Long> ids);

    //批量删除setmeal_dish
    Integer batchDeleteSetmealDish(@Param("ids")List<Long> ids);

    //修改setmeal状态
    @Update("update setmeal set status = #{status} where id = #{id}")
    Integer updateSetmealStatus(@Param("status") Integer status , @Param("id") Integer id);

    //查询setmeal_dish根据id
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDishBody> selectSetmealDishById(@Param("setmealId") long setmealId);

    //查询setmeal根据id
    @Select("select * from setmeal where id = #{id}")
    SetmealAndDishBody selectSetmealById(@Param("id") long setmealId);

}
