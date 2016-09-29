package com.neo.config;

import com.neo.persistence.NEODao;
import com.neo.service.NEOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ServiceConfig {
    @Resource
    private NEODao neoDao;

    @Bean
    public NEOService neoService(){
        return new NEOService(neoDao);
    }
}

