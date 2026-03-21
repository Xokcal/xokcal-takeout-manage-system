package com.example.cangqiong.Pojo.Category;

import lombok.Data;

@Data
public class AddCategoryBody {
    private Integer id;
    private String name;
    private Integer sort;
    private Integer type;

    public AddCategoryBody() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public AddCategoryBody(Integer id, String name, Integer sort, Integer type) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.type = type;
    }
}
