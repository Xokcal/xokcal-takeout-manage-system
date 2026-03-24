package com.example.cangqiong.Pojo.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepoetUserStatisticVO {
    private String dateList;
    private String newUserList;
    private String totalUserList;
}
