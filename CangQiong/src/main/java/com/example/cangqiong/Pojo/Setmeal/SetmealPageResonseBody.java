package com.example.cangqiong.Pojo.Setmeal;

import com.example.cangqiong.Pojo.EmployeeBody;

import java.util.List;

public class SetmealPageResonseBody {
    private long total;
    private List<SetmealAndDishBody> records;

    public SetmealPageResonseBody() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<SetmealAndDishBody> getRecords() {
        return records;
    }

    public void setRecords(List<SetmealAndDishBody> records) {
        this.records = records;
    }

    public SetmealPageResonseBody(long total, List<SetmealAndDishBody> records) {
        this.total = total;
        this.records = records;
    }
}
