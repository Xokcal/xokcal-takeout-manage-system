package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Dish.DishAndDishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishSelectPageBody;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    //新增dish
    @Options(useGeneratedKeys = true , keyProperty = "id")
    @Insert("INSERT INTO dish (name, category_id, price, image, description, status) " +
            "VALUES (#{name}, #{categoryId}, #{price}, #{image}, #{description}, #{status})")
    Integer addNewDish(DishAndDishFlavorBody dishAndDishFlavorBody);

    //批量新增flavor
    Integer batchAddFlavor(List<DishFlavorBody> dishFlavorBodies);

    //获取匹配的分类名字
    @Select("select c.name from category c where id = #{id}")
    String getCategoryNameById(@Param("id") Integer id);

    //查询dish所有数据和category_id对应的name
    List<DishAndDishFlavorBody> selectDishPage(DishSelectPageBody dishSelectPageBody);

    //得到菜品总数
    @Select("select count(*) from dish;")
    Long total();

    //删除flavor
    @Select("delete from dish_flavor where dish_id = #{dishId}")
    Integer deleteFlavor(@Param("dishId") Long dishId);

    //更新餐品
    @Update({
            "UPDATE dish",
            "SET name = #{name},",
            "    category_id = #{categoryId},",
            "    price = #{price},",
            "    image = #{image},",
            "    description = #{description},",
            "    status = #{status},",
            "    update_time = CURRENT_TIMESTAMP", // 自动更新时间为当前时间
            "WHERE id = #{id}" // 根据主键更新，避免全表更新
    })
    int updateDish(DishAndDishFlavorBody dishAndDishFlavorBody);

    //批量删除风味
    Integer batchDeleteFlavor(List<Long> ids);

    //批量删除餐品
    Integer batchDeleteDish(List<Long> ids);

    //根据id查询菜品
    @Select("select * from dish where id = #{id}")
    DishAndDishFlavorBody selectDishById(Long id);

    //根据id查询匹配的风味
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavorBody> selectFlavorByDishId(@Param("dishId") Long dishId);

    //根据分类id查询餐品
    @Select("select * from dish where category_id = #{categoryId}")
    List<DishAndDishFlavorBody> selectDishByCategoryId(@Param("categoryId") Long categoryId);

    //菜品起售、停售
    @Update("update dish set status = #{status} where id = #{id}")
    Integer updateDishStatus(@Param("status") Integer status , @Param("id") Long id);


}
