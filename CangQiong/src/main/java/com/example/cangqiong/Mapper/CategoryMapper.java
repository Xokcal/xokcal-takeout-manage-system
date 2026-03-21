package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Category.AddCategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryBody;
import com.example.cangqiong.Pojo.Category.UpdateCategoryBody;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //分类分页查询
    List<CategoryBody> queryCategoryPage(@Param("name") String name ,@Param("type") Integer type
            , @Param("start") Integer start , @Param("pageSize") Integer pageSize);

    //分类总数
    @Select("select count(*) from category")
    long getCategoryTotal();

    //新增分类
    @Insert("insert into category(name , sort , type) values (#{name} , #{sort} , #{type})")
    Integer addNewCategory(AddCategoryBody addCategoryBody);

    //修改分类
    @Update("update category set name = #{name},sort = #{sort}")
    Integer updateCategory(UpdateCategoryBody updateCategoryBody);

    //修改状态
    @Update("update category set status = #{status} where id = #{id}")
    Integer updateCategoryStatus(@Param("status") Integer status , @Param("id") Integer id);

    //删除分类根据id
    @Delete("delete from category where id = #{id}")
    Integer deleteCategoryById(@Param("id") Integer id);

    //根据type查询分类
    @Select("select * from category where type = #{type}")
    List<CategoryBody> selectCategoryByType(@Param("type") Integer type);
}
