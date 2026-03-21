package com.example.cangqiong.Pojo.Dish;

import com.example.cangqiong.Pojo.Setmeal.SetmealAndDishBody;
import lombok.Data;

import java.util.List;

@Data
public class DishPageResponseBody {
    private Long total;
    private List<DishAndDishFlavorBody> records;

    public DishPageResponseBody() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DishAndDishFlavorBody> getRecords() {
        return records;
    }

    public void setRecords(List<DishAndDishFlavorBody> records) {
        this.records = records;
    }

    public DishPageResponseBody(Long total, List<DishAndDishFlavorBody> records) {
        this.total = total;
        this.records = records;
    }
}
