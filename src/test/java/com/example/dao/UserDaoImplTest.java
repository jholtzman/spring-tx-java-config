package com.example.dao;

import com.google.common.base.Optional;
import com.example.dao.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserDaoImplTest extends BaseMySQLTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testById() throws Exception {
        jdbcTemplate.execute("insert into mgdb.users (email) values ('bob@example.com')");
        int userId = lastInsertId(jdbcTemplate);
        Optional<User> userOptional = userDao.byId(userId);
        assertThat(userOptional.isPresent(), equalTo(true));
        User user = userOptional.get();
        assertThat(user.getEmailAddress(), equalTo("bob@example.com"));
    }

    @Test
    public void testByIdNoUser() throws Exception {
        jdbcTemplate.execute("insert into mgdb.users (email) values ('bob@example.com')");
        int userId = lastInsertId(jdbcTemplate);
        jdbcTemplate.execute("delete from mgdb.users where id=" + userId);

        Optional<User> userOptional = userDao.byId(userId);
        assertThat(userOptional.isPresent(), equalTo(false));
    }
}
