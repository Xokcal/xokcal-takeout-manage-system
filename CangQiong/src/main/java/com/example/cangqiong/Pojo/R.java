package com.example.cangqiong.Pojo;

import com.example.cangqiong.Common.Exception.BusinessException;

import java.util.List;

public class R<T> {
    private Integer code;
    private T data;
    private Object msg;

    //返回集合
    public R ok(List<T> list){
        R r = new R();
        r.setCode(1);
        r.setData(list);
        r.setMsg("成功！！");
        return r;
    }

    //返回类
    public R ok(Object o){
        R r = new R();
        r.setCode(1);
        r.setData(o);
        r.setMsg("成功！！");
        return r;
    }

    //是否通过
    public R ok(Integer i){
        R r = new R();
        r.setCode(i);
        r.setData(null);
        r.setMsg("成功！！");
        return r;
    }

    //是否通过
    public R ok(Integer i , Object o){
        R r = new R();
        r.setCode(1);
        r.setData(i);
        r.setMsg("成功！！");
        return r;
    }

    //是否通过
    public R ok(Integer i , String s){
        R r = new R();
        r.setCode(i);
        r.setData(s);
        r.setMsg("成功！！");
        return r;
    }

    //全局变量报错
    public R error(Exception e){
        R r = new R();
        r.setCode(0);
        r.setData(null);
        r.setMsg(e.getMessage());
        return r;
    }

    public R error(BusinessException e){
        R r = new R();
        r.setCode(0);
        r.setData(null);
        r.setMsg(e.getMessage());
        return r;
    }

    //更新类
    public R ok(String s){
        R r = new R();
        r.setCode(1);
        r.setData(s);
        r.setMsg("成功！！");
        return r;
    }

    //空参
    public R ok(){
        R r = new R();
        r.setCode(1);
        r.setData(null);
        r.setMsg("成功！！");
        return r;
    }

    //错误类
    public R error(Object o){
        R r = new R();
        r.setCode(1);
        r.setData(null);
        r.setMsg((String) o);
        return r;
    }

    public R() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
