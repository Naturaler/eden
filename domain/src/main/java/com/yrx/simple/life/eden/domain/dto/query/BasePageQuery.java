package com.yrx.simple.life.eden.domain.dto.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BasePageQuery {
    private Integer pageNum;
    private Integer pageSize;
}
