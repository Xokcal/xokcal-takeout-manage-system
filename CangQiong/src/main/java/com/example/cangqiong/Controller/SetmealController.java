package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Pojo.Setmeal.PageSetmealRequetBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealAndDishBody;
import com.example.cangqiong.Pojo.Setmeal.SetmealPageResonseBody;
import com.example.cangqiong.Service.Implement.SetmealImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class SetmealController {

    @Autowired
    private SetmealImpl setmealImpl;

    //分页查询套餐
    @GetMapping("/setmeal/page")
    @Operation(summary = "分页查询" , description = "分页查询套餐")
    R selectSetmealPage(PageSetmealRequetBody pageSetmealRequetBody){
        SetmealPageResonseBody r = setmealImpl.pageSetmeal(pageSetmealRequetBody);
        return new R().ok(r);
    }

    //新增套餐
    @PostMapping("/setmeal")
    @Operation(summary = "新增" , description = "新增套餐")
    R addNewSetmeal(@RequestBody SetmealAndDishBody setmealAndDishBody){
        Integer r = setmealImpl.addSetmeal(setmealAndDishBody);
        return new R().ok(r);
    }

    //修改套餐
    @PutMapping("/setmeal")
    @Operation(summary = "修改" , description = "修改套餐")
    R updateSetmeal(@RequestBody SetmealAndDishBody setmealAndDishBody){
        Integer r = setmealImpl.updateSetmeal(setmealAndDishBody);
        return new R().ok(r);
    }

    //删除套餐和套餐菜品
    @DeleteMapping("/setmeal")
    @Operation(summary = "批量删除" , description = "删除套餐和套餐菜品")
    R deleteSetmealAndDish(@RequestParam("ids") List<Long> ids){
        Integer r = setmealImpl.deleteSetmealAndSetmealDish(ids);
        return new R().ok(r);
    }

    //修改状态
    @PostMapping("/setmeal/status/{status}")
    @Operation(summary = "修改" , description = "修改菜品停售状态")
    R updateSetmealStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = setmealImpl.updateSetmealStatus(status, id);
        return new R().ok(r);
    }

    //根据id查询套餐
    @GetMapping("/setmeal/{id}")
    @Operation(summary = "根据id查询" , description = "根据id查询套餐")
    R selectSetmealById(@PathVariable("id") long id){
        SetmealAndDishBody r = setmealImpl.selectSetmealById(id);
        return new R().ok(r);
    }
}
