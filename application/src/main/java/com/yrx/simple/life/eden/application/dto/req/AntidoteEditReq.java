package com.yrx.simple.life.eden.application.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AntidoteEditReq {
    @NotNull(message = "ID不能为空")
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "关键字不能为空")
    private String key;
    @NotBlank(message = "解药不能为空")
    private String val;
    @NotBlank(message = "备注不能为空")
    private String remark;
}
