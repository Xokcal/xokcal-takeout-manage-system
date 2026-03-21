package com.example.cangqiong.Pojo.Dish;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DishAndDishFlavorBody {
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    private String categoryName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<DishFlavorBody> flavors;

    public DishAndDishFlavorBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<DishFlavorBody> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<DishFlavorBody> flavors) {
        this.flavors = flavors;
    }

    public DishAndDishFlavorBody(Long id, String name, Long categoryId, BigDecimal price, String image, String description, Integer status, String categoryName, LocalDateTime createTime, LocalDateTime updateTime, List<DishFlavorBody> flavors) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.categoryName = categoryName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.flavors = flavors;
    }
}
