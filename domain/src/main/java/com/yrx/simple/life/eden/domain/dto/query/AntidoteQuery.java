package com.yrx.simple.life.eden.domain.dto.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AntidoteQuery extends BasePageQuery {
    private String title;
    private String key;
    private String remark;
}
