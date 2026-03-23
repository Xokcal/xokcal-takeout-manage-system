package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Mapper.ReportMapper;
import com.example.cangqiong.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;


}
