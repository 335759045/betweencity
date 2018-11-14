package com.betweencity.tm.betweencity.bean;

/**
 * Created by Administrator on 2017/12/19.
 */

public class HttpResult<T> {
    /**
     * data : {"token":"4h34hfd73h34h"}
     * errocode : 0	请求成功，无错误1	参数错误2	操作失败
     * erromsg : 登录成功
     */

    private  T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "HttpResult{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}