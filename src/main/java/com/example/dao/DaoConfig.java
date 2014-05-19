package com.example.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {

    @Bean
    public UserDao providesUserDao(@Value("${driverClassName:com.mysql.jdbc.Driver}") String driverClassName,
                                   @Value("${jdbcUrl:jdbc:mysql://localhost:3306/mgdb?zeroDateTimeBehavior=convertToNull}") String jdbcUrl,
                                   @Value("${userName:root}") String userName,
                                   @Value("${password:r00t}") String password) {
        return new UserDaoImpl(providesDataSource(driverClassName, jdbcUrl, userName, password));
    }

    @Bean(name = "dataSource")
    public DataSource providesDataSource(@Value("${driverClassName:com.mysql.jdbc.Driver}") String driverClassName,
                                         @Value("${jdbcUrl:jdbc:mysql://localhost:3306/mgdb?zeroDateTimeBehavior=convertToNull}") String jdbcUrl,
                                         @Value("${userName:root}") String userName,
                                         @Value("${password:r00t}") String password) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(jdbcUrl);
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate providesJdbcTemplate(@Value("${driverClassName:com.mysql.jdbc.Driver}") String driverClassName,
                                             @Value("${jdbcUrl:jdbc:mysql://localhost:3306/mgdb?zeroDateTimeBehavior=convertToNull}") String jdbcUrl,
                                             @Value("${userName:root}") String userName,
                                             @Value("${password:r00t}") String password) {
        return new JdbcTemplate(providesDataSource(driverClassName, jdbcUrl, userName, password));
    }

}
