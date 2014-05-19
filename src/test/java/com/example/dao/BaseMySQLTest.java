package com.example.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseMySQLTest.TestConfig.class})
abstract public class BaseMySQLTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected int lastInsertId() {
        return jdbcTemplate.queryForInt("select LAST_INSERT_ID()");
    }

    @Configuration
    @Import(DaoConfig.class)
    @PropertySource("classpath:/jdbc.properties")
    @EnableTransactionManagement
    public static class TestConfig {
        @Bean
        public PlatformTransactionManager providesTransactionManager(ListableBeanFactory beanFactory) {
            return new DataSourceTransactionManager(beanFactory.getBean(DataSource.class));
        }
    }
}

