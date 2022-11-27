package com.yrx.simple.life.eden.application.service.impl;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.assembler.AntidoteAssembler;
import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteAddReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteEditReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
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
    public HttpResponse<String> add(AntidoteAddReq req) {
        Antidote antidote = antidoteAssembler.convertReqToEntity(req);
        antidoteRepository.save(antidote);
        return HttpResponse.success(ApiResponse.success());
    }

    @Override
    public HttpResponse<PageInfo<Antidote>> list(AntidoteListReq req) {
        AntidoteQuery query = antidoteAssembler.convertReqToEntity(req);
        List<Antidote> list = antidoteRepository.list(query);
        return HttpResponse.success(ApiResponse.success(new PageInfo<>(list)));
    }

    @Override
    public HttpResponse<AntidoteRsp> get(Long id) {
        Antidote antidote = antidoteRepository.get(id);
        // 密码加密
//        antidote.encrypt();
        AntidoteRsp antidoteRsp = antidoteAssembler.convertEntityToRsp(antidote);
        return HttpResponse.success(ApiResponse.success(antidoteRsp));
    }

    @Override
    public ApiResponse<Antidote> delete(Long id) {
        return ApiResponse.success(antidoteRepository.remove(id));
    }

    @Override
    public ApiResponse<Antidote> edit(AntidoteEditReq req) {
        Antidote antidote = antidoteAssembler.convertEditReqToEntity(req);
        return ApiResponse.success(antidoteRepository.save(antidote));
    }
}
