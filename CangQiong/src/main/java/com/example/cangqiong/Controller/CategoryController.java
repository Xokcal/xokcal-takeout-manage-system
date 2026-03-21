package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Jwt.JwtUtil;
import com.example.cangqiong.Pojo.Category.AddCategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryPageResonseBody;
import com.example.cangqiong.Pojo.Category.UpdateCategoryBody;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Service.Implement.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryImpl categoryImpl;

    @Autowired
    private JwtUtil jwtUtil;

    //分类分页查询
    @GetMapping("/category/page")
    R queryCategoryPage(
            @RequestParam(value = "name" ,required = false) String name
            , @RequestParam("page") Integer page
            , @RequestParam("pageSize") Integer pageSize
            , @RequestParam(value = "type" , required = false) Integer type){
        CategoryPageResonseBody r = categoryImpl.pageCategory(name ,type, page, pageSize);
        return new R().ok(r);
    }

    //新增分类
    @PostMapping("/category")
    R addCategory(@RequestBody AddCategoryBody addCategoryBody){
        Integer r = categoryImpl.addCategory(addCategoryBody);
        return new R().ok(r);
    }

    //修改分类
    @PutMapping("/category")
    R updateCategory(@RequestBody UpdateCategoryBody updateCategoryBody){
        Integer r = categoryImpl.updateCategory(updateCategoryBody);
        return new R().ok(r);
    }

    //修改状态
    @PostMapping("/category/status/{status}")
    R updateCategoryStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = categoryImpl.updateCategoryStatus(status, id);
        return new R().ok(r);
    }

    //根据type查询分类
    @GetMapping("/category/list")
    R selectCategoryByType(@RequestParam("type") Integer type){
        List<CategoryBody> r = categoryImpl.selectCategoryByType(type);
        return new R().ok(r);
    }

    //根据id删除
    @DeleteMapping("/category")
    R deleteCategoryById(@RequestParam("id") Integer id){
        Integer r = categoryImpl.deleteCategoryById(id);
        return new R().ok(r);
    }

}
