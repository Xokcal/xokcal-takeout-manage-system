package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Oss.Oss;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Service.Implement.CommonImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CommonController {

    @Autowired
    private CommonImpl commonImpl;

    @PostMapping("/common/upload")
    R uploadFile(@RequestBody MultipartFile file){
        String r = commonImpl.uploadFile(file);
        return new R().ok(r);
    }
}
