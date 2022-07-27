package com.yrx.simple.life.eden.application.service;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;
import com.yrx.simple.life.eden.domain.entity.Antidote;

public interface AntidoteService {
    HttpResponse<String> add(AntidoteReq req);

    HttpResponse<PageInfo<Antidote>> list(AntidoteListReq req);

    HttpResponse<AntidoteRsp> get(Long id);
}
