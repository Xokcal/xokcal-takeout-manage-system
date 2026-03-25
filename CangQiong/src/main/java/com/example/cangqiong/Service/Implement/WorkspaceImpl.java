package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.WorkspaceMapper;
import com.example.cangqiong.Pojo.Workspace.WorkspaceBusinessVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceDishVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceOrderVO;
import com.example.cangqiong.Pojo.Workspace.WorkspaceSetmealVO;
import com.example.cangqiong.Service.Constant.WorkspaceConstant;
import com.example.cangqiong.Service.WorkspaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Slf4j
@Service
public class WorkspaceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;

    //得到每日运用报表数据
    @Override
    public WorkspaceBusinessVO getReportBusinessView(){
        WorkspaceBusinessVO workspaceBusinessVO = new WorkspaceBusinessVO().builder()
                .newUsers(workspaceMapper.getTotalNewUser())
                .validOrderCount(workspaceMapper.getValidOrder())
                .orderCompletionRate(workspaceMapper.getCompleteAccuracy()
                        .setScale(WorkspaceConstant.BIGDECIMAL_REOUDING_MODE_VALUE , RoundingMode.HALF_UP))
                .unitPrice(workspaceMapper.getAvgAmount().setScale(WorkspaceConstant
                        .BIGDECIMAL_REOUDING_MODE_VALUE , RoundingMode.HALF_UP))
                .turnover(workspaceMapper.getTotalAmount().setScale(WorkspaceConstant
                        .BIGDECIMAL_REOUDING_MODE_VALUE , RoundingMode.HALF_UP))
                .build();
        if (!CheckIsValidUtil.isValid(workspaceBusinessVO)){
            log.warn(WorkspaceConstant.GET_DAILY_MANAGE_DATE_RESULT_ERROR);
            throw new BusinessException(WorkspaceConstant.GET_DAILY_MANAGE_DATE_RESULT_ERROR
                    ,WorkspaceConstant.CODE_BEHIND);
        }
        return workspaceBusinessVO;
    }

    //查询订单管理数据
    @Override
    public WorkspaceOrderVO getOrderView(){
        WorkspaceOrderVO workspaceOrderVO = new WorkspaceOrderVO().builder()
                .allOrders(workspaceMapper.getOrdersTotal())
                .cancelledOrders(workspaceMapper.getOrderCancel())
                .completedOrders(workspaceMapper.getOrderFinish())
                .deliveredOrders(workspaceMapper.getOrderWillDelivery())
                .waitingOrders(workspaceMapper.getOrderWillConfirm())
                .build();
        if (!CheckIsValidUtil.isValid(workspaceOrderVO)){
            log.warn(WorkspaceConstant.GET_ORDER_VIEW_RESULT_ERROR);
            throw new BusinessException(WorkspaceConstant.GET_ORDER_VIEW_RESULT_ERROR
                    ,WorkspaceConstant.CODE_BEHIND);
        }
        return workspaceOrderVO;
    }

    //查询套餐总览
    @Override
    public WorkspaceSetmealVO getSetmealView(){
        WorkspaceSetmealVO workspaceSetmealVO = new WorkspaceSetmealVO().builder()
                .discontinued(workspaceMapper.getSetmealStop())
                .sold(workspaceMapper.getSetmealRun())
                .build();
        if (!CheckIsValidUtil.isValid(workspaceSetmealVO)){
            log.warn(WorkspaceConstant.GET_SETMEAL_VIEW_RESULT_ERROR);
            throw new BusinessException( WorkspaceConstant.GET_SETMEAL_VIEW_RESULT_ERROR
                    ,WorkspaceConstant.CODE_BEHIND);
        }
        return workspaceSetmealVO;
    }

    //菜品总览
    @Override
    public WorkspaceDishVO getDishView(){
        WorkspaceDishVO workspaceDishVO = new WorkspaceDishVO().builder()
                .discontinued(workspaceMapper.getDishStop())
                .sold(workspaceMapper.getDishRun())
                .build();
        if (!CheckIsValidUtil.isValid(workspaceDishVO)){
            log.warn(WorkspaceConstant.GET_DISH_VIEW_RESULT_ERROR);
            throw new BusinessException(WorkspaceConstant.GET_DISH_VIEW_RESULT_ERROR
                    ,WorkspaceConstant.CODE_BEHIND);
        }
        return workspaceDishVO;
    }


}
