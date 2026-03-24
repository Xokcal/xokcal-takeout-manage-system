package com.example.cangqiong.Pojo.Workspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceOrderVO {
    private Integer allOrders;
    private Integer cancelledOrders;
    private Integer completedOrders;
    private Integer deliveredOrders;
    private Integer waitingOrders;
}
