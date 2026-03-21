package com.example.cangqiong.Pojo.Setmeal;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SetmealDishBody {
    private Long id;
    private Integer copies;
    private Long dishId;
    private String name;
    private BigDecimal price;
    private Long setmealId;

    public SetmealDishBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    public SetmealDishBody(Long id, Integer copies, Long dishId, String name, BigDecimal price, Long setmealId) {
        this.id = id;
        this.copies = copies;
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.setmealId = setmealId;
    }
}
