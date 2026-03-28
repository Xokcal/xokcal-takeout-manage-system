package com.example.cangqiong.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePageRequestBody {
    private String name;
    private Integer page;
    private Integer pageSize;
}