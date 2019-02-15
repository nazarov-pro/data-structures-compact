package org.shahin.nazarov.jdbi.service;

import org.shahin.nazarov.jdbi.AbstractJdbiService;
import org.shahin.nazarov.jdbi.dao.UserDao;
import org.shahin.nazarov.jdbi.model.User;


public class UserService extends AbstractJdbiService<User, Integer> {

    public UserService() {
        super(UserDao.class);
    }
}
