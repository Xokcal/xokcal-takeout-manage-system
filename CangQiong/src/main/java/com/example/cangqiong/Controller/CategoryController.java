package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Common.Jwt.JwtUtil;
import com.example.cangqiong.Pojo.Category.*;
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

    //分类分页查询
    @GetMapping("/category/page")
    @Operation(summary = "分页查询" , description = "获取分类的分页查询")
    R queryCategoryPage(CategoryPageRequestBody categoryPageRequestBody){
        CategoryPageResonseBody r = categoryImpl.pageCategory(categoryPageRequestBody);
        return new R().ok(r);
    }

    //新增分类
    @PostMapping("/category")
    @Operation(summary = "新增" , description = "添加新的分类类型")
    R addCategory(@RequestBody AddCategoryBody addCategoryBody){
        Integer r = categoryImpl.addCategory(addCategoryBody);
        return new R().ok(r);
    }

    //修改分类
    @PutMapping("/category")
    @Operation(summary = "修改" , description = "修改分类信息")
    R updateCategory(@RequestBody UpdateCategoryBody updateCategoryBody){
        Integer r = categoryImpl.updateCategory(updateCategoryBody);
        return new R().ok(r);
    }

    //修改状态
    @PostMapping("/category/status/{status}")
    @Operation(summary = "修改" , description = "修改分页是否启动（0：关闭，1：启动）")
    R updateCategoryStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = categoryImpl.updateCategoryStatus(status, id);
        return new R().ok(r);
    }

    //根据type查询分类
    @GetMapping("/category/list")
    @Operation(summary = "查询" , description = "根据分类的类型查询所有匹配的分类")
    R selectCategoryByType(@RequestParam("type") Integer type){
        List<CategoryBody> r = categoryImpl.selectCategoryByType(type);
        return new R().ok(r);
    }

    //根据id删除
    @DeleteMapping("/category")
    @Operation(summary = "删除" , description = "根据id删除分类")
    R deleteCategoryById(@RequestParam("id") Integer id){
        Integer r = categoryImpl.deleteCategoryById(id);
        return new R().ok(r);
    }

}

