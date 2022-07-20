package com.yrx.simple.life.eden.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public static ApiResponse<String> success() {
        ApiResponse<String> success = new ApiResponse<>();
        success.setCode(200);
        success.setMsg("成功");
        return success;
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> success = new ApiResponse<>();
        success.setCode(200);
        success.setMsg("成功");
        success.setData(data);
        return success;
    }

    public static ApiResponse<String> fail() {
        ApiResponse<String> fail = new ApiResponse<>();
        fail.setCode(500);
        fail.setMsg("失败");
        return fail;
    }

    public static ApiResponse<String> fail(Integer code, String msg) {
        ApiResponse<String> fail = new ApiResponse<>();
        fail.setCode(code);
        fail.setMsg(msg);
        return fail;
    }
}
