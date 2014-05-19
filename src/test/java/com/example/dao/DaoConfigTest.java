package com.example.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class DaoConfigTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(PropertyPlaceholderConfigurer.class, DaoConfig.class);
    }

    @Test
    public void testProvidesUserDao() throws Exception {
        UserDao userDao = applicationContext.getBean(UserDao.class);
        assertThat(userDao, notNullValue());
    }

    @Test
    public void testProvidesDataSource() throws Exception {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        assertThat(dataSource, notNullValue());
    }

    @Test
    public void testProvidesJdbcTemplate() throws Exception {
        JdbcTemplate bean = applicationContext.getBean(JdbcTemplate.class);
        assertThat(bean, notNullValue());
    }
}