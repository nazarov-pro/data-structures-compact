package org.shahin.nazarov.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.shahin.nazarov.HikariDs;

public class JdbiConfig {
    private static JdbiConfig jdbiConfig;
    private final Jdbi jdbi;

    private JdbiConfig(){
        this.jdbi = Jdbi.create(HikariDs.getInstance().getHikariDataSource());
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    public static JdbiConfig getInstance() {
        if(jdbiConfig == null)
            jdbiConfig = new JdbiConfig();
        return jdbiConfig;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }
}
