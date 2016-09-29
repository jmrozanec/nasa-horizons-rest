package com.neo.config;

import com.neo.horizon.HorizonCoordinateRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@Configuration
public class GeneralConfig {

    @Bean
    public HorizonCoordinateRetriever horizonCoordinateRetriever(){
        return new HorizonCoordinateRetriever();
    }

    //TODO SSL: http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#howto-configure-tomcat

    @Bean//Kudos to: https://github.com/spring-projects/spring-boot/issues/540
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
