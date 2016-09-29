package com.neo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Profile("test")
    public static class TestDBConfig {
        @Bean
        public DataSource datasource() {
            return dataSource("127.0.0.1", "neo", "root", "s4msung");
        }
    }

    @Profile("dev")
    public static class DevDBConfig {
        @Bean
        public DataSource datasource() {
            return dataSource("127.0.0.1", "neo", "root", "s4msung");
        }
    }

    @Profile("prod")
    public static class ProdDBConfig {
        @Bean
        public DataSource datasource() {
            return dataSource("127.0.0.1", "neo", "root", "s4msung");
        }
    }

    static DataSource dataSource(String host, String database, String username, String credential){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s/%s", host, database));
        dataSource.setUsername(username);
        dataSource.setPassword(credential);
        dataSource.addConnectionProperty("allowMultiQueries", "true");
        return dataSource;
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory){
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory factory) {
        return factory.getSessionFactory();
    }

}

