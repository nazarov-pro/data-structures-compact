package org.shahin.nazarov.jdbi.mapper;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.MapTo;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.shahin.nazarov.jdbi.JdbiMapper;
import org.shahin.nazarov.jdbi.model.User;

import java.util.Collection;

public interface UserMapper extends JdbiMapper<User, Integer> {

    String TABLE_NAME = "\"USER\"";

    @Override
    @SqlUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))")
    void createTable();

    @Override
    @SqlUpdate("INSERT INTO " + TABLE_NAME + "(id, username, password) VALUES (:id, :username, :password)")
    void insert(@BindBean User model);

    @Override
    @SqlUpdate("UPDATE " + TABLE_NAME + " set username = :username, password = :password where id = :id")
    void update(@BindBean User model, @Bind("id") Integer key);

    @Override
    @SqlUpdate("DELETE FROM " + TABLE_NAME + " where id = :id")
    void remove(@Bind("id") Integer key);

    @Override
    @SqlQuery("SELECT * FROM " + TABLE_NAME + " where id = :id")
    @RegisterBeanMapper(User.class)
    User get(@Bind("id") Integer key, @MapTo Class<User> mClass);

    @Override
    @SqlQuery("SELECT * FROM " + TABLE_NAME )
    @RegisterBeanMapper(User.class)
    Collection<User> list(@MapTo Class<User> mClass);
}
