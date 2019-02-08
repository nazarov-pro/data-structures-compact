package org.shahin.nazarov.db.tunning.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shahin.nazarov.db.tunning.domain.Test1;

import java.util.Collection;

public interface Test1Mapper {

    String tableName = "test3";

    @Select("SELECT * from " + Test1Mapper.tableName)
    Collection<Test1> listTest1();

    @Select("SELECT * from " + Test1Mapper.tableName + " where id = '${id}'")
    Test1 selectById(@Param("id") String id);


    @Insert("INSERT into " + Test1Mapper.tableName + "(ID,NAME,GENDER,DATA,AMOUNT,STATUS,TIMESTAMP)" +
            " VALUES( '${id}', #{name}, #{gender}, '${data}', #{amount}, #{status}, #{timestamp})")
    void insertTest1(Test1 test1);

}
