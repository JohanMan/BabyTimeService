package com.johan.common.entity;

import java.util.HashMap;
import java.util.Map;

public class ResultBody {

    // 成功码
    public static final int CODE_OK = 200;
    // 成功信息
    public static final String MESSAGE_OK = "success";

    // 通用错误码
    public static final int CODE_ERROR = 201;
    // 未登录错误码
    public static final int CODE_NOT_LOGIN = 101;
    // 没有权限错误码
    public static final int CODE_NOT_PERMISSION = 102;

    private int code;
    private String message;
    private Map<String, Object> data;

    public ResultBody setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultBody setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResultBody setData(String key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, value);
        return this;
    }

    public static ResultBody ok() {
        ResultBody body = new ResultBody();
        body.setCode(CODE_OK);
        body.setMessage(MESSAGE_OK);
        return body;
    }

    public static ResultBody error(int code, String message) {
        ResultBody body = new ResultBody();
        body.setCode(code);
        body.setMessage(message);
        return body;
    }

    public static ResultBody error(String message) {
        ResultBody body = new ResultBody();
        body.setCode(CODE_ERROR);
        body.setMessage(message);
        return body;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
