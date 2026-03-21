package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Dish.DishAndDishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishPageResponseBody;
import com.example.cangqiong.Pojo.Dish.DishSelectPageBody;

import java.util.List;

public interface DishService {
    public Integer addNewDishMain(DishAndDishFlavorBody dishAndDishFlavorBody);
    public DishPageResponseBody selectDishPageMain(DishSelectPageBody dishSelectPageBody);
    public Integer updateDishMain(DishAndDishFlavorBody dishAndDishFlavorBody);
    public Integer deleteDishAndFlavorMain(List<Long> ids);
    public DishAndDishFlavorBody selectDishByIdMain(Long id);
    public List<DishAndDishFlavorBody> selectDishByCategoryIdMain(Long categoryId);
    public Integer updateDishStatusMain(Integer status , Long id);
}
