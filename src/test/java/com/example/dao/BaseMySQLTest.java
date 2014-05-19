package com.example.dao;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseMySQLTest.TestConfig.class})
abstract public class BaseMySQLTest {

    protected int lastInsertId(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForInt("select LAST_INSERT_ID()");
    }

    @Configuration
    @Import(DaoConfig.class)
    @PropertySource("classpath:/jdbc.properties")
    public static class TestConfig {

        @Bean
        public static PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean
        public static PropertyPlaceholderConfigurer placeholderConfigurer() {
            return new PropertyPlaceholderConfigurer();
        }
    }
}

