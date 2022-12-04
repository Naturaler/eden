package com.yrx.simple.life.eden.web.api;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.AntidoteEditReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteListReq;
import com.yrx.simple.life.eden.application.dto.req.AntidoteAddReq;
import com.yrx.simple.life.eden.application.dto.rsp.AntidoteRsp;
import com.yrx.simple.life.eden.application.service.AntidoteService;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("antidote")
@Slf4j
public class AntidoteApi {
    @Resource
    private AntidoteService antidoteService;

    @GetMapping("/all")
    public HttpResponse<PageInfo<Antidote>> getAll() {
        return antidoteService.list(new AntidoteListReq());
    }

    @PostMapping("add")
    public HttpResponse<String> add(@Validated @RequestBody AntidoteAddReq req) {
        return antidoteService.add(req);
    }

    @PostMapping("update")
    public HttpResponse<Antidote> update(@Validated @RequestBody AntidoteEditReq req) {
        return HttpResponse.success(antidoteService.update(req));
    }

    @PostMapping("list")
    public HttpResponse<PageInfo<Antidote>> list(@RequestBody AntidoteListReq req) {
        return antidoteService.list(req);
    }

    @GetMapping("/get")
    public HttpResponse<AntidoteRsp> get(@RequestParam Long id) {
        return antidoteService.get(id);
    }

    @GetMapping("/delete")
    public HttpResponse<Antidote> delete(@RequestParam Long id) {
        return HttpResponse.success(antidoteService.delete(id));
    }
}
