package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Pojo.Workspace.WorkspaceBusinessVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceDishVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceOrderVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceSetmealVO;
import com.example.cangqiong.Service.Implement.WorkspaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class WorkspaceController {

    @Autowired
    private WorkspaceImpl workspaceImpl;

    //得到每日运用报表数据
    @GetMapping("/workspace/businessData")
    @Operation(summary = "查询" , description = "得到每日运用报表数据")
    R getBusinessReport(){
        WorkspaceBusinessVO r = workspaceImpl.getReportBusinessView();
        return new R().ok(r);
    }

    //得到套餐总览
    @GetMapping("/workspace/overviewSetmeals")
    @Operation(summary = "查询" , description = "得到套餐总览")
    R getSetmealView(){
        WorkspaceSetmealVO r = workspaceImpl.getSetmealView();
        return new R().ok(r);
    }

    //菜品总览
    @GetMapping("/workspace/overviewDishes")
    @Operation(summary = "查询" , description = "菜品总览")
    R getDishView(){
        WorkspaceDishVO r = workspaceImpl.getDishView();
        return new R().ok(r);
    }

    //订单管理
    @GetMapping("/workspace/overviewOrders")
    @Operation(summary = "查询" , description = "订单管理")
    R getOrderView(){
        WorkspaceOrderVO r = workspaceImpl.getOrderView();
        return new R().ok(r);
    }
}
