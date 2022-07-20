package com.yrx.simple.life.eden.web.api;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class RootApi {

    @GetMapping()
    public ApiResponse<String> root() {
        log.info("req root api");
        return ApiResponse.success();
    }
}
