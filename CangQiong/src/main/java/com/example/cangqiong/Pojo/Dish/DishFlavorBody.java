package com.example.cangqiong.Pojo.Dish;

import lombok.Data;

@Data
public class DishFlavorBody {
    private Long id;
    private Long dishId;
    private String name;
    private String value;

    public DishFlavorBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DishFlavorBody(Long id, Long dishId, String name, String value) {
        this.id = id;
        this.dishId = dishId;
        this.name = name;
        this.value = value;
    }
}
