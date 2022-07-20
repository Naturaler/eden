package com.yrx.simple.life.eden.application.service;

import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;

import java.util.List;

public interface AntidoteService {
    HttpResponse<String> add(AntidoteReq req);

    HttpResponse<List<AntidoteRsp>> list(AntidoteListReq req);
}
