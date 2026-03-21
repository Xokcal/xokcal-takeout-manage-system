package com.example.cangqiong.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class EmployeePageResonseBody {
    private long total;
    private List<EmployeeBody> records;

    public EmployeePageResonseBody() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<EmployeeBody> getRecords() {
        return records;
    }

    public void setRecords(List<EmployeeBody> records) {
        this.records = records;
    }

    public EmployeePageResonseBody(long total, List<EmployeeBody> records) {
        this.total = total;
        this.records = records;
    }
}
