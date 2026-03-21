package com.example.cangqiong.Pojo.Category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPageResonseBody {
    private long total;
    private List<CategoryBody> records;

    public CategoryPageResonseBody() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<CategoryBody> getRecords() {
        return records;
    }

    public void setRecords(List<CategoryBody> records) {
        this.records = records;
    }

    public CategoryPageResonseBody(long total, List<CategoryBody> records) {
        this.total = total;
        this.records = records;
    }
}
