package org.shahin.nazarov;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.juliuskrah.tutorial");
        EntityManager em = emf.createEntityManager();
        Connection connection = em.unwrap(SessionImpl.class).connection();
        Database database = null;
        Liquibase liquibase = null;

        try {
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            liquibase = new Liquibase("dbChangelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("test");
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
