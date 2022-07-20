package com.yrx.simple.life.eden.application.service.impl;

import com.yrx.simple.life.eden.application.assembler.AntidoteAssembler;
import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;
import com.yrx.simple.life.eden.application.service.AntidoteService;
import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.domain.persistence.AntidoteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AntidoteServiceImpl implements AntidoteService {
    @Resource
    private AntidoteAssembler antidoteAssembler;
    @Resource
    private AntidoteRepository antidoteRepository;

    @Override
    public HttpResponse<String> add(AntidoteReq req) {
        Antidote antidote = antidoteAssembler.convertReqToEntity(req);
        antidoteRepository.save(antidote);
        return HttpResponse.success(ApiResponse.success());
    }

    @Override
    public HttpResponse<List<AntidoteRsp>> list(AntidoteListReq req) {
        AntidoteQuery query = antidoteAssembler.convertReqToEntity(req);
        List<Antidote> list = antidoteRepository.list(query);
        return HttpResponse.success(ApiResponse.success(antidoteAssembler.convertEntitiesToRsp(list)));
    }
}
