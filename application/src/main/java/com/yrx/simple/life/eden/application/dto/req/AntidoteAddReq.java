package com.yrx.simple.life.eden.application.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AntidoteAddReq {
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "关键字不能为空")
    private String key;
    @NotBlank(message = "解药不能为空")
    private String val;
    @NotBlank(message = "备注不能为空")
    private String remark;
}
