package com.example.cangqiong.Pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EmployeePageResonseBody implements Serializable {
    private long total;
    private Object records;

    public EmployeePageResonseBody() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getRecords() {
        return records;
    }

    public void setRecords(Object records) {
        this.records = records;
    }

    public EmployeePageResonseBody(long total, Object records) {
        this.total = total;
        this.records = records;
    }
}
