package com.yrx.simple.life.eden.application.service;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;

import java.util.List;

public interface AntidoteService {
    ApiResponse<String> add(AntidoteReq req);

    ApiResponse<List<AntidoteRsp>> list(AntidoteListReq req);
}
