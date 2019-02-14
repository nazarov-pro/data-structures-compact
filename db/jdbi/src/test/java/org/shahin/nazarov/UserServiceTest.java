package org.shahin.nazarov;

import org.junit.Test;
import org.shahin.nazarov.jdbi.model.User;
import org.shahin.nazarov.jdbi.service.UserService;

import static java.lang.System.out;

public class UserServiceTest {

    @Test
    public void createTable(){
        UserService userService = new UserService();
        userService.list().forEach( u -> out.println(u));

        User user = userService.get(1);

        user.setUsername("fdsf");

        userService.update(user, 2);

        userService.list().forEach( u -> out.println(u));
    }
}
