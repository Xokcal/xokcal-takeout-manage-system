package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Category.AddCategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryPageResonseBody;
import com.example.cangqiong.Pojo.Category.UpdateCategoryBody;

import java.util.List;

public interface CategoryService {
    public CategoryPageResonseBody pageCategory(String name ,Integer type , Integer page , Integer pageSize);
    public Integer addCategory(AddCategoryBody addCategoryBody);
    public Integer updateCategory(UpdateCategoryBody updateCategoryBody);
    public Integer updateCategoryStatus(Integer status , Integer id);
    public Integer deleteCategoryById(Integer id);
    public List<CategoryBody> selectCategoryByType(Integer type);
}
