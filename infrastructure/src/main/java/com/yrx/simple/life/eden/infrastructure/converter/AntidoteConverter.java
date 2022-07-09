package com.yrx.simple.life.eden.infrastructure.converter;

import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AntidoteConverter {
    AntidotePo convertEntityToPo(Antidote antidote);
}
