package com.example.cangqiong.Pojo.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageRequestBody {
    private String name;
    private Integer page;
    private Integer pageSize;
    private String type;
}
