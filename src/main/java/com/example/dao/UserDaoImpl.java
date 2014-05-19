package com.example.dao;

//import com.google.common.base.Optional;

import com.example.dao.mapper.UserRowMapper;
import com.google.common.base.Optional;
import com.example.dao.model.User;
import org.springframework.dao.DataAccessException;

import javax.sql.DataSource;

public class UserDaoImpl extends AbstractDao implements UserDao {

    public UserDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Optional<User> byId(int userId) {
        try {
            User user = jdbcTemplate.queryForObject("select * from mgdb.users where id=?", new UserRowMapper(), userId);
            return Optional.of(user);
        } catch (DataAccessException e) {
            return Optional.absent();
        }
    }
}
