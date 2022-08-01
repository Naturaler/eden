package com.yrx.simple.life.eden.application.dto.req;

import lombok.Data;

@Data
public abstract class BasePageReq {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
