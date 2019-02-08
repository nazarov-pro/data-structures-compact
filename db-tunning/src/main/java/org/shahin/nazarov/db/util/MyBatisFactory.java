package org.shahin.nazarov.db.util;

import lombok.extern.java.Log;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.InputStream;

@Log
public class MyBatisFactory {

    private SqlSessionFactory sqlSessionFactory;


    public MyBatisFactory(){ initialize(); }

    public void initialize(){
        DataSource dataSource = new HikariDataSource().getDataSource();

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment  = new Environment("developpment", transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMappers("org.shahin.nazarov.db.mybatis.mapper");

        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);
    }

    public final SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream resourceStream = new MyBatisFactory().getClass().getClassLoader()
                    .getResourceAsStream(Constants.MYBATIS_CONFIG_FILE);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
        }
        return sqlSessionFactory;
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
