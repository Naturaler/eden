package com.yrx.simple.life.eden.application.service.impl;

import com.github.pagehelper.PageInfo;
import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import com.yrx.simple.life.eden.application.dto.req.NoteListReq;
import com.yrx.simple.life.eden.application.service.NoteService;
import com.yrx.simple.life.eden.domain.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {
    private final Map<Long, Note> map = new HashMap<>();

    @Override
    public HttpResponse<PageInfo<Note>> list(NoteListReq req) {
        List<Note> list = new ArrayList<>(map.values());
        return HttpResponse.success(ApiResponse.success(new PageInfo<>(list)));
    }

    @Override
    public HttpResponse<Note> get(Long id) {
        return HttpResponse.success(ApiResponse.success(map.get(id)));
    }

    @Override
    public HttpResponse<Note> update(Note note) {
        map.put(note.getId(), note);
        return HttpResponse.success(ApiResponse.success(note));
    }

    @Override
    public HttpResponse<Note> add(Note note) {
        if (note.getId() == null) {
            int id = map.size() + 1;
            note.setId(Long.valueOf(id + ""));
        }
        map.put(note.getId(), note);
        return HttpResponse.success(ApiResponse.success(note));
    }

    @Override
    public HttpResponse<Note> delete(Long id) {
        Note note = map.get(id);
        map.remove(id);
        return HttpResponse.success(ApiResponse.success(note));
    }
}
