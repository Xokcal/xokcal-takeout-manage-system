package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Setmeal.PageSetmealRequetBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealAndDishBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealPageResonseBody;

import java.util.List;

public interface SetmealService {
    public SetmealPageResonseBody pageSetmeal(PageSetmealRequetBody pageSetmealRequetBody);
    public Integer addSetmeal(SetmealAndDishBody setmealAndDishBody);
    public Integer updateSetmeal(SetmealAndDishBody setmealAndDishBody);
    public Integer deleteSetmealAndSetmealDish(List<Long> ids);
    public Integer updateSetmealStatus(Integer status , Integer id);
    public SetmealAndDishBody selectSetmealById(long id);
}
