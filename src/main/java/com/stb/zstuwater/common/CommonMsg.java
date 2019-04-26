package com.stb.zstuwater.common;

public class CommonMsg {

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public CommonMsg(Object data) {
        this.data = data;
        this.code = 0;
        this.msg = "success";
        this.count = 0;
    }

    public CommonMsg() {
        this.code = 0;
        this.msg = "success";
        this.count = 0;
    }

    public CommonMsg(Integer count, Object data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}

