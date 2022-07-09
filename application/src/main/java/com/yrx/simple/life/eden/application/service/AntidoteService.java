package com.yrx.simple.life.eden.application.service;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;

public interface AntidoteService {
    ApiResponse<String> add(AntidoteReq req);
}
