package com.yrx.simple.life.eden.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HttpResponse<T> {
    private Integer httpCode;
    private String httpMsg;
    private ApiResponse<T> apiResponse;

    public static HttpResponse<String> success() {
        HttpResponse<String> success = new HttpResponse<>();
        success.setHttpCode(200);
        success.setHttpMsg("成功");
        return success;
    }

    public static <T> HttpResponse<T> success(ApiResponse<T> data) {
        HttpResponse<T> success = new HttpResponse<>();
        success.setHttpCode(200);
        success.setHttpMsg("成功");
        success.setApiResponse(data);
        return success;
    }

    public static HttpResponse<String> accessDenied() {
        HttpResponse<String> fail = new HttpResponse<>();
        fail.setHttpCode(403);
        fail.setHttpMsg("无访问权限");
        return fail;
    }

    public static HttpResponse<String> invalidArguments(String msg) {
        HttpResponse<String> fail = new HttpResponse<>();
        fail.setHttpCode(400);
        fail.setHttpMsg(msg);
        return fail;
    }
}
