package org.shahin.nazarov;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariDs {
    private static HikariDs hikariDs;
    private final HikariDataSource hikariDataSource;

    private HikariDs(){
        String path = getClass().getClassLoader()
                .getResource("hikari.properties").getPath();
        HikariConfig hikariConfig = new HikariConfig(path);
        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDs getInstance() {
        if(hikariDs == null)
            hikariDs = new HikariDs();
        return hikariDs;
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}