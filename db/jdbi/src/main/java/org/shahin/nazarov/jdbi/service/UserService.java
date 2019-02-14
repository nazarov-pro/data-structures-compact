package org.shahin.nazarov.jdbi.service;

import org.shahin.nazarov.jdbi.AbstractJdbiService;
import org.shahin.nazarov.jdbi.mapper.UserMapper;
import org.shahin.nazarov.jdbi.model.User;


public class UserService extends AbstractJdbiService<User, Integer> {

    public UserService() {
        super(UserMapper.class);
    }
}
