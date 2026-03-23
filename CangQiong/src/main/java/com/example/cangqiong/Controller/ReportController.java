package com.example.cangqiong.Controller;

import com.example.cangqiong.Service.Implement.ReportImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class ReportController {

    @Autowired
    private ReportImpl reportImpl;

    //导出Excel报表接口
    @GetMapping("/report/export")
    String reportExcelApi(){
        return "ok";
    }
}
