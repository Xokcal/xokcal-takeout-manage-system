package com.example.cangqiong.Common.StringUtil;

import org.springframework.stereotype.Component;

import java.util.List;


public class StringUtil {

    public StringUtil(){}

    //将集合所有元素集成为一行String，中间用“+”号相连
    public static String arrElementAssembledStringSeparateIsDot(List<String> arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1){
                stringBuilder.append(arr.get(i));
                return stringBuilder.toString();
            }
            stringBuilder.append(arr.get(i));
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

}
