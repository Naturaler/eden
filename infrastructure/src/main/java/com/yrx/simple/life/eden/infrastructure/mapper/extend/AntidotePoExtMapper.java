package com.yrx.simple.life.eden.infrastructure.mapper.extend;

import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;

import java.util.List;

public interface AntidotePoExtMapper {
    Long insert(AntidotePo po);

    List<Antidote> list(AntidoteQuery query);
}
