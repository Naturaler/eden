package com.yrx.simple.life.eden.application.dto.req;

import lombok.Data;

@Data
public abstract class BasePageReq {
    private Integer pageNum;
    private Integer pageSize;

}
