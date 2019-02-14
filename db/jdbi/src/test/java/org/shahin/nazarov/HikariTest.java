package org.shahin.nazarov;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HikariTest {

    @Test
    public void test() {
        HikariDataSource dataSource = HikariDs.getInstance().getHikariDataSource();
        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.prepareStatement("select CURRENT_TIMESTAMP").executeQuery();
            resultSet.next();
            Timestamp time = resultSet.getTimestamp(1);
            System.out.println(time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
