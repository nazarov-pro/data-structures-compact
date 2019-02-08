package org.shahin.nazarov.db.tunning.util;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Log
public class HikariDataSource {
    private com.zaxxer.hikari.HikariDataSource hikariDataSource;

    public HikariDataSource(){
        initialize();
    }

    @PostConstruct
    public void initialize(){
        log.info("Data Source Initializing...");
        HikariConfig config = new HikariConfig("/hikari.properties");
        hikariDataSource = new com.zaxxer.hikari.HikariDataSource(config);
    }


    @PreDestroy
    public void releaseResource() {
        log.info("close data source");
        if(hikariDataSource != null) {
            hikariDataSource.close();
        }
    }


    public com.zaxxer.hikari.HikariDataSource getDataSource() {
        return hikariDataSource;
    }
}
