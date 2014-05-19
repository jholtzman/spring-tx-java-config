package com.example.dao;

import com.google.common.base.Optional;
import com.example.dao.model.User;

public interface UserDao {
    Optional<User> byId(int userId);
}
