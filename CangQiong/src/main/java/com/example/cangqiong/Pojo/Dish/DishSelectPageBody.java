package com.example.cangqiong.Pojo.Dish;

import lombok.Data;

@Data
public class DishSelectPageBody {
    private Long categoryId;
    private String name;
    private Integer page;
    private Integer pageSize;
    private Integer status;
    private Integer start;

    public DishSelectPageBody() {
    }

    public DishSelectPageBody(Long categoryId, String name, Integer page, Integer pageSize, Integer status, Integer start) {
        this.categoryId = categoryId;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
        this.status = status;
        this.start = start;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
