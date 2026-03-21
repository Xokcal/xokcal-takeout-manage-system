package com.example.cangqiong.Pojo.Setmeal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSetmealRequetBody {
    private Integer categoryId;
    private String name;
    private Integer page;
    private Integer pageSize;
    private Integer status;
}
