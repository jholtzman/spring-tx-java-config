package com.example.dao.mapper;

import com.example.dao.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        int id = rs.getInt("id");
        user.setId(id);

        String email = rs.getString("email");
        user.setEmailAddress(email);

        return user;
    }
}
