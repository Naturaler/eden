package com.yrx.simple.life.eden.domain.persistence;

import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;

import java.util.List;

public interface AntidoteRepository {
    Antidote save(Antidote item);

    List<Antidote> list(AntidoteQuery query);

    Antidote get(Long id);

    Antidote remove(Long id);
}
