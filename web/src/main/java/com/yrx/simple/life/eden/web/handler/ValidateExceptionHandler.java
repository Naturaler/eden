package com.yrx.simple.life.eden.web.handler;

import com.yrx.simple.life.eden.application.dto.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数校验异常全局处理
 * <a href="https://juejin.cn/post/6844904003684302861#heading-8">异常解释</a>
 */
@RestControllerAdvice
@Slf4j
public class ValidateExceptionHandler {
    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";

    // <1> 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public HttpResponse<String> bindExceptionHandler(BindException e) {
        log.debug("触发了 bindExceptionHandler");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errors = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return HttpResponse.invalidArguments(errors);
    }

    // <2> 处理 Content-type: application/json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.debug("触发了 methodArgumentNotValidExceptionHandler");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errors = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return HttpResponse.invalidArguments(errors);
    }

    // <3> 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpResponse<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.debug("触发了 constraintViolationExceptionHandler");
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String errors = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return HttpResponse.invalidArguments(errors);
    }
}
