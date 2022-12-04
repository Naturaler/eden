package com.yrx.simple.life.eden.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.yrx.simple.life.eden.domain.dto.query.AntidoteQuery;
import com.yrx.simple.life.eden.domain.entity.Antidote;
import com.yrx.simple.life.eden.domain.enumeration.DelFlagEnum;
import com.yrx.simple.life.eden.domain.persistence.AntidoteRepository;
import com.yrx.simple.life.eden.infrastructure.converter.AntidoteConverter;
import com.yrx.simple.life.eden.infrastructure.mapper.AntidotePoMapper;
import com.yrx.simple.life.eden.infrastructure.mapper.extend.AntidotePoExtMapper;
import com.yrx.simple.life.eden.infrastructure.po.AntidotePo;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
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
        po.setDelFlag(DelFlagEnum.NORMAL.getFlag());
        if (po.getId() == null) {
            Long id = antidotePoExtMapper.insert(po);
            antidote.setId(id);
        } else {
            antidotePoMapper.updateByPrimaryKey(po);
        }
        return antidote;
    }

    @Override
    public List<Antidote> list(AntidoteQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return antidotePoExtMapper.list(query);
    }

    @Override
    public Antidote get(Long id) {
        AntidotePo antidotePo = antidotePoMapper.selectByPrimaryKey(id);
        return antidoteConverter.convertPoToEntity(antidotePo);
    }

    @Override
    public Antidote remove(Long id) {
        AntidotePo po = antidotePoMapper.selectByPrimaryKey(id);
        po.setDelFlag(Byte.valueOf("1"));
        antidotePoMapper.updateByPrimaryKey(po);
        return antidoteConverter.convertPoToEntity(po);
    }

    public static void main(String[] args) {
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb://127.0.0.1:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }
        }
    }
}
