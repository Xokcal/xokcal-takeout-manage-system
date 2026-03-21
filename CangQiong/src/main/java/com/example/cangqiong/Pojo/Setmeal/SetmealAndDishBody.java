package com.example.cangqiong.Pojo.Setmeal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SetmealAndDishBody {
    private Long id;

    private Long categoryId;

    private String description;

    private String image;

    private String name;

    private BigDecimal price;

    private Integer status;

    private List<SetmealDishBody> setmealDishes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public SetmealAndDishBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SetmealDishBody> getSetmealDishes() {
        return setmealDishes;
    }

    public void setSetmealDishes(List<SetmealDishBody> setmealDishes) {
        this.setmealDishes = setmealDishes;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SetmealAndDishBody(Long id, Long categoryId, String description, String image, String name, BigDecimal price, Integer status, List<SetmealDishBody> setmealDishes, LocalDateTime updateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.status = status;
        this.setmealDishes = setmealDishes;
        this.updateTime = updateTime;
    }
}
