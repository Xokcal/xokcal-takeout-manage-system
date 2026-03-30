package com.example.cangqiong.Common.ThreadLocal;

public class UserTokenStore {
    private static ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    public static void setUserId(Long userId){
        USER_ID.set(userId);
    }

    public static Long getUserId(){
        return USER_ID.get();
    }

    public static void removeUserId(){
        USER_ID.remove();
    }
}
