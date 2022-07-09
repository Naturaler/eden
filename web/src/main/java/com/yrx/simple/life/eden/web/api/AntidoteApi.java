package com.yrx.simple.life.eden.web.api;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.service.AntidoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("antidote")
public class AntidoteApi {
    @Resource
    private AntidoteService antidoteService;

    @PostMapping("add")
    public ApiResponse<String> add(@RequestBody AntidoteReq req) {
        return antidoteService.add(req);
    }
}
