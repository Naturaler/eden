package com.yrx.simple.life.eden.web.api;

import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;
import com.yrx.simple.life.eden.application.service.AntidoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("antidote")
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AntidoteApi {
    @Resource
    private AntidoteService antidoteService;

    @GetMapping("/all")
    public HttpResponse<List<AntidoteRsp>> getAll() {
        return antidoteService.list(new AntidoteListReq());
    }

    @PostMapping("add")
    public HttpResponse<String> add(@RequestBody AntidoteReq req) {
        log.info("receive antidote add req: {}", req);
        return antidoteService.add(req);
    }

    @PostMapping("list")
    public HttpResponse<List<AntidoteRsp>> list(@RequestBody AntidoteListReq req) {
        log.info("receive antidote list req: {}", req);
        return antidoteService.list(req);
    }
}
