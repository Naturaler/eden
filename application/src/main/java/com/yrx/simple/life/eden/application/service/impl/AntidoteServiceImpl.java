package com.yrx.simple.life.eden.application.service.impl;

import com.yrx.simple.life.eden.application.assembler.AntidoteAssembler;
import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.service.AntidoteService;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.domain.persistence.AntidoteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AntidoteServiceImpl implements AntidoteService {
    @Resource
    private AntidoteAssembler antidoteAssembler;
    @Resource
    private AntidoteRepository antidoteRepository;

    @Override
    public ApiResponse<String> add(AntidoteReq req) {
        Antidote antidote = antidoteAssembler.convertReqToEntity(req);
        antidoteRepository.save(antidote);
        return ApiResponse.success();
    }
}
