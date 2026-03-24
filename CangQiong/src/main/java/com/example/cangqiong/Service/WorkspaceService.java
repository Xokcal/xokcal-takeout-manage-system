package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.Workspace.WorkspaceBusinessVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceDishVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceOrderVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceSetmealVO;

public interface WorkspaceService {
    public WorkspaceBusinessVO getReportBusinessView();
    public WorkspaceSetmealVO getSetmealView();
    public WorkspaceDishVO getDishView();
    public WorkspaceOrderVO getOrderView();
}
