package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Pojo.Dish.DishAndDishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishPageResponseBody;
import com.example.cangqiong.Pojo.Dish.DishSelectPageBody;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Service.Implement.DishImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class DishController {

    @Autowired
    private DishImpl dishImpl;

    //新增菜品
    @PostMapping("/dish")
    @Operation(summary = "新增" , description = "新增菜品种类")
    R addNewDish(@RequestBody DishAndDishFlavorBody dishAndDishFlavorBody){
        Integer r = dishImpl.addNewDishMain(dishAndDishFlavorBody);
        return new R().ok(r);
    }

    //分页查询菜品
    @GetMapping("/dish/page")
    @Operation(summary = "分页查询" , description = "分页查询菜品")
    R selectDishPage(DishSelectPageBody dishSelectPageBody){
        DishPageResponseBody r = dishImpl.selectDishPageMain(dishSelectPageBody);
        return new R().ok(r);
    }

    //修改菜品
    @PutMapping("/dish")
    @Operation(summary = "修改" , description = "修改菜品")
    R updateDish(@RequestBody DishAndDishFlavorBody dishAndDishFlavorBody){
        Integer r = dishImpl.updateDishMain(dishAndDishFlavorBody);
        return new R().ok(r);
    }

    //批量删除
    @DeleteMapping("/dish")
    @Operation(summary = "批量删除" , description = "批量删除菜品")
    R updateDish(@RequestParam("ids") List<Long> ids){
        Integer r = dishImpl.deleteDishAndFlavorMain(ids);
        return new R().ok(r);
    }

    //根据id查询菜品
    @GetMapping("/dish/{id}")
    @Operation(summary = "根据id查询" , description = "根据菜品id查询菜品")
    R selectDishById(@PathVariable("id") Long id){
        DishAndDishFlavorBody r = dishImpl.selectDishByIdMain(id);
        return new R().ok(r);
    }

    //根据id查询菜品
    @GetMapping("/dish/list")
    @Operation(summary = "根据id查询" , description = "根据食物分类id查询菜品")
    R selectDishByCategoryId(@RequestParam("categoryId") Long categoryId){
        List<DishAndDishFlavorBody> r = dishImpl.selectDishByCategoryIdMain(categoryId);
        return new R().ok(r);
    }

    //菜品起售、停售
    @PostMapping("/dish/status/{status}")
    @Operation(summary = "修改状态" , description = "修改菜品起售、停售（0：停售，1：起售）")
    R updateDishStatus(@RequestParam("id") Long id,@PathVariable("status") Integer status){
        Integer r = dishImpl.updateDishStatusMain(status, id);
        return new R().ok(r);
    }


}

