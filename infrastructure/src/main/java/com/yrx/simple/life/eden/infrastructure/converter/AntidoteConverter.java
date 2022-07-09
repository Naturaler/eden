package com.yrx.simple.life.eden.infrastructure.converter;

import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AntidoteConverter {
    @Mappings({
            @Mapping(target = "createTime", expression = "java(new java.util.Date())"),
            @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    })
    AntidotePo convertEntityToPo(Antidote antidote);
}
