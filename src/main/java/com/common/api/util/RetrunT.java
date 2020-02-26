package com.common.api.util;

public class RetrunT {

    private int code;

    private String msg;

    private Object datas;

    RetrunT(){}

    public static RetrunT success(){
        RetrunT retrunT = new RetrunT();
        retrunT.code = 0;
        retrunT.msg = "ok";
        return  retrunT;
    }

    public static RetrunT fail(){
        RetrunT retrunT = new RetrunT();
        retrunT.code = 500;
        retrunT.msg = "fail";
        return  retrunT;
    }

    public static RetrunT success(Object datas){
        RetrunT retrunT = new RetrunT();
        retrunT.code = 0;
        retrunT.msg = "ok";
        retrunT.datas = datas;
        return  retrunT;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}
