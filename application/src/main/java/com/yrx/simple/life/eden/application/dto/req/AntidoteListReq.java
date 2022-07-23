package com.yrx.simple.life.eden.application.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AntidoteListReq extends BasePageReq {
    private String title;
    private String key;
    private String remark;
}
