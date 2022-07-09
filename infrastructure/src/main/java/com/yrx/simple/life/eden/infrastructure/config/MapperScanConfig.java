package com.yrx.simple.life.eden.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({
        "com.yrx.simple.life.eden.infrastructure.mapper",
        "com.yrx.simple.life.eden.infrastructure.mapper.extend",
})
public class MapperScanConfig {
}
