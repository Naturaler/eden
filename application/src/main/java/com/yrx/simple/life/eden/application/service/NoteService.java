package com.yrx.simple.life.eden.application.service;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.NoteListReq;
import com.yrx.simple.life.eden.domain.entity.Note;

public interface NoteService {
    HttpResponse<PageInfo<Note>> list(NoteListReq req);

    HttpResponse<Note> get(Long id);

    HttpResponse<Note> update(Note note);

    HttpResponse<Note> add(Note req);

    HttpResponse<Note> delete(Long id);
}
