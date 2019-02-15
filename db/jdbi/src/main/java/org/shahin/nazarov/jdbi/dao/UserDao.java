package org.shahin.nazarov.jdbi.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.MapTo;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.shahin.nazarov.jdbi.JdbiDao;
import org.shahin.nazarov.jdbi.model.User;

import java.util.Collection;

public interface UserDao extends JdbiDao<User, Integer> {

    String TABLE_NAME = "\"USER\"";

    @Override
    @SqlUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))")
    void createTable();

    @Override
    @SqlUpdate("INSERT INTO " + TABLE_NAME + "(id, username, password) VALUES (:id, :username, :password)")
    void insert(@BindBean User model);

    @Override
    @SqlBatch("INSERT INTO " + TABLE_NAME + "(id, username, password) VALUES (:id, :username, :password)")
    void insert(@BindBean Collection<User> models);

    @Override
    @SqlUpdate("UPDATE " + TABLE_NAME + " set username = :username, password = :password, id = :id where id = :_id")
    void update(@BindBean User model, @Bind("_id") Integer key);

    @Override
    @SqlBatch("UPDATE " + TABLE_NAME + " set username = :username, password = :password, id = :id where id = :_id")
    void update(@BindBean Collection<User> models, @Bind("_id") Collection<Integer> keys);

    @Override
    @SqlUpdate("DELETE FROM " + TABLE_NAME + " where id = :id")
    void remove(@Bind("id") Integer key);

    @Override
    @SqlBatch("DELETE FROM " + TABLE_NAME + " where id = :id")
    void remove(@Bind("id") Collection<Integer> keys);

    @Override
    @SqlQuery("SELECT * FROM " + TABLE_NAME + " where id = :id")
    @RegisterBeanMapper(User.class)
    User get(@Bind("id") Integer key, @MapTo Class<User> mClass);

    @Override
    @SqlQuery("SELECT * FROM " + TABLE_NAME )
    @RegisterBeanMapper(User.class)
    @MaxRows(5)
    Collection<User> list(@MapTo Class<User> mClass);

    @Override
    @SqlQuery("SELECT COUNT(*) FROM " + TABLE_NAME )
    long size();
}
