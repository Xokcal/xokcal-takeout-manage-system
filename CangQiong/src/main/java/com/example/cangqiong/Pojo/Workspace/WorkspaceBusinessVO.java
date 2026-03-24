package com.example.cangqiong.Pojo.Workspace;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceBusinessVO {
    private Long id;
    private Integer newUsers;
    private BigDecimal orderCompletionRate;
    private BigDecimal turnover;
    private BigDecimal unitPrice;
    private Integer validOrderCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statDate;
    private Long storeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
