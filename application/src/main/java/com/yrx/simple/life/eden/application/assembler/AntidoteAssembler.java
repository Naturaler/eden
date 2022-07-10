package com.yrx.simple.life.eden.application.assembler;

import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;
import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AntidoteAssembler {
    Antidote convertReqToEntity(AntidoteReq req);

    AntidoteQuery convertReqToEntity(AntidoteListReq req);

    AntidoteRsp convertEntityToRsp(Antidote antidote);
    List<AntidoteRsp> convertEntitiesToRsp(List<Antidote> list);
}
