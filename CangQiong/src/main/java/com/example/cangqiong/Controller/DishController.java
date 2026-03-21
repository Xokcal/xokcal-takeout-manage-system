package com.example.cangqiong.Controller;

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
    R addNewDish(@RequestBody DishAndDishFlavorBody dishAndDishFlavorBody){
        Integer r = dishImpl.addNewDishMain(dishAndDishFlavorBody);
        return new R().ok(r);
    }

    //分页查询菜品
    @GetMapping("/dish/page")
    R selectDishPage(DishSelectPageBody dishSelectPageBody){
        DishPageResponseBody r = dishImpl.selectDishPageMain(dishSelectPageBody);
        return new R().ok(r);
    }

    //修改菜品
    @PutMapping("/dish")
    R updateDish(@RequestBody DishAndDishFlavorBody dishAndDishFlavorBody){
        Integer r = dishImpl.updateDishMain(dishAndDishFlavorBody);
        return new R().ok(r);
    }

    //批量删除
    @DeleteMapping("/dish")
    R updateDish(@RequestParam("ids") List<Long> ids){
        Integer r = dishImpl.deleteDishAndFlavorMain(ids);
        return new R().ok(r);
    }

    //根据id查询菜品
    @GetMapping("/dish/{id}")
    R selectDishById(@PathVariable("id") Long id){
        DishAndDishFlavorBody r = dishImpl.selectDishByIdMain(id);
        return new R().ok(r);
    }

    //根据id查询菜品
    @GetMapping("/dish/list")
    R selectDishByCategoryId(@RequestParam("categoryId") Long categoryId){
        List<DishAndDishFlavorBody> r = dishImpl.selectDishByCategoryIdMain(categoryId);
        return new R().ok(r);
    }

    //菜品起售、停售
    @PostMapping("/dish/status/{status}")
    R updateDishStatus(@RequestParam("id") Long id,@PathVariable("status") Integer status){
        Integer r = dishImpl.updateDishStatusMain(status, id);
        return new R().ok(r);
    }


}
