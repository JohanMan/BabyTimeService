package com.johan.common.config;

import com.johan.common.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SnowFlake getSnowFlake() {
        return new SnowFlake(0, 0);
    }

}
