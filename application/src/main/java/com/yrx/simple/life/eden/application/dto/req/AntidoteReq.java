package com.yrx.simple.life.eden.application.dto.req;

import lombok.Data;

@Data
public class AntidoteReq extends BasePageReq {
    private String title;
    private String key;
    private String val;
    private String remark;
}
