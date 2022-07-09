package com.yrx.simple.life.eden.infrastructure.repository;

import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.domain.persistence.AntidoteRepository;
import com.yrx.simple.life.eden.infrastructure.converter.AntidoteConverter;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AntidoteRepositoryImpl implements AntidoteRepository {
    @Resource
    private AntidoteMapper antidoteMapper;
    @Resource
    private AntidoteConverter antidoteConverter;

    @Override
    public Antidote save(Antidote antidote) {
        AntidotePo po = antidoteConverter.convertEntityToPo(antidote);
        if (po.getId() == null) {
            Long id =  antidoteMapper.insert(po);
            antidote.setId(id);
        }else {
            antidoteMapper.updateById(po);
        }
        return antidote;
    }
}
