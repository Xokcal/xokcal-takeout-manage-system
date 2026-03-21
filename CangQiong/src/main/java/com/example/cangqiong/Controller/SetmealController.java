package com.example.cangqiong.Controller;

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
    R selectSetmealPage(PageSetmealRequetBody pageSetmealRequetBody){
        SetmealPageResonseBody r = setmealImpl.pageSetmeal(pageSetmealRequetBody);
        return new R().ok(r);
    }

    //新增套餐
    @PostMapping("/setmeal")
    R addNewSetmeal(@RequestBody SetmealAndDishBody setmealAndDishBody){
        Integer r = setmealImpl.addSetmeal(setmealAndDishBody);
        return new R().ok(r);
    }

    //修改套餐
    @PutMapping("/setmeal")
    R updateSetmeal(@RequestBody SetmealAndDishBody setmealAndDishBody){
        Integer r = setmealImpl.updateSetmeal(setmealAndDishBody);
        return new R().ok(r);
    }

    //删除setmeal和setmeal_dish
    @DeleteMapping("/setmeal")
    R deleteSetmealAndDish(@RequestParam("ids") List<Long> ids){
        Integer r = setmealImpl.deleteSetmealAndSetmealDish(ids);
        return new R().ok(r);
    }

    //修改状态
    @PostMapping("/setmeal/status/{status}")
    R updateSetmealStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = setmealImpl.updateSetmealStatus(status, id);
        return new R().ok(r);
    }

    //根据id查询套餐
    @GetMapping("/setmeal/{id}")
    R selectSetmealById(@PathVariable("id") long id){
        SetmealAndDishBody r = setmealImpl.selectSetmealById(id);
        return new R().ok(r);
    }
}
