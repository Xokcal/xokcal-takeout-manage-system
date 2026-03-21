package com.example.cangqiong.Pojo.Setmeal;

import lombok.Data;

import java.util.List;

@Data
public class BatchDeleteIdsRequsetBody {
    private List<Long> ids;

    public BatchDeleteIdsRequsetBody() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public BatchDeleteIdsRequsetBody(List<Long> ids) {
        this.ids = ids;
    }
}
