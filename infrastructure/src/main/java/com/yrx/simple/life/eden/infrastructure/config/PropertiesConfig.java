package com.yrx.simple.life.eden.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:application-infrastructure.properties",
        "classpath:application-infrastructure-${spring.profiles.active}.properties"
})
public class PropertiesConfig {
}
