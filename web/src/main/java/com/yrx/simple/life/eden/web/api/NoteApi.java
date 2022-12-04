package com.yrx.simple.life.eden.web.api;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.NoteListReq;
import com.yrx.simple.life.eden.application.service.NoteService;
import com.yrx.simple.life.eden.domain.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("note")
@Slf4j
public class NoteApi {

    @Resource
    private NoteService noteService;

    @PostMapping("list")
    public HttpResponse<PageInfo<Note>> list(@RequestBody NoteListReq req) {
        return noteService.list(req);
    }

    @GetMapping("/get")
    public HttpResponse<Note> get(@RequestParam Long id) {
        return noteService.get(id);
    }

    @PostMapping("/update")
    public HttpResponse<Note> get(@RequestBody Note note) {
        return noteService.update(note);
    }

    @PostMapping("add")
    public HttpResponse<Note> add(@Validated @RequestBody Note req) {
        return noteService.add(req);
    }

    @GetMapping("/delete")
    public HttpResponse<Note> delete(@RequestParam Long id) {
        return noteService.delete(id);
    }
}
