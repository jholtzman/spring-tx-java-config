package com.example.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

abstract public class AbstractDao {
    final protected JdbcTemplate jdbcTemplate;

    protected AbstractDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
