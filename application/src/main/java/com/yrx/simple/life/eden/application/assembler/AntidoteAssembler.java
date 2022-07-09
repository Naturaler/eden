package com.yrx.simple.life.eden.application.assembler;

import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AntidoteAssembler {
    Antidote convertReqToEntity(AntidoteReq req);
}
