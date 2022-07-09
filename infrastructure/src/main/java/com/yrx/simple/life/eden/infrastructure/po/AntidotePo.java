package com.yrx.simple.life.eden.infrastructure.po;

import lombok.Data;

import java.util.Date;

@Data
public class AntidotePo {
    private Long id;
    private String key;
    private String val;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
