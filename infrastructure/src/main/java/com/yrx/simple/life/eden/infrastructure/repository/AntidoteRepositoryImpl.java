package com.yrx.simple.life.eden.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.domain.persistence.AntidoteRepository;
import com.yrx.simple.life.eden.infrastructure.converter.AntidoteConverter;
import com.yrx.simple.life.eden.infrastructure.mapper.AntidotePoMapper;
import com.yrx.simple.life.eden.infrastructure.mapper.extend.AntidotePoExtMapper;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AntidoteRepositoryImpl implements AntidoteRepository {
    @Resource
    private AntidotePoMapper antidotePoMapper;
    @Resource
    private AntidotePoExtMapper antidotePoExtMapper;
    @Resource
    private AntidoteConverter antidoteConverter;

    @Override
    public Antidote save(Antidote antidote) {
        AntidotePo po = antidoteConverter.convertEntityToPo(antidote);
        if (po.getId() == null) {
            Long id =  antidotePoExtMapper.insert(po);
            antidote.setId(id);
        }else {
            antidotePoMapper.updateByPrimaryKey(po);
        }
        return antidote;
    }

    @Override
    public List<Antidote> list(AntidoteQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return antidotePoExtMapper.list(query);
    }
}
