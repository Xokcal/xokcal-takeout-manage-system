package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Category.*;

import java.util.List;

public interface CategoryService {
    public CategoryPageResonseBody pageCategory(CategoryPageRequestBody categoryPageRequestBody);
    public Integer addCategory(AddCategoryBody addCategoryBody);
    public Integer updateCategory(UpdateCategoryBody updateCategoryBody);
    public Integer updateCategoryStatus(Integer status , Integer id);
    public Integer deleteCategoryById(Integer id);
    public List<CategoryBody> selectCategoryByType(Integer type);
}
