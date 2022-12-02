package com.yrx.simple.life.eden.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum DelFlagEnum {
    NORMAL(Byte.valueOf("0"), "正常"),
    DELETED(Byte.valueOf("1"), "已删除");
    private Byte flag;
    private String desc;

}
