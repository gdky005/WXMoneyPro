package com.gdky005.wxmoneydemo;

public class BaseBean<T> {


    /**
     * code : 0
     * message : ok
     * result : {"id":1,"name":"我的零钱","money":"58.00"}
     */

    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
