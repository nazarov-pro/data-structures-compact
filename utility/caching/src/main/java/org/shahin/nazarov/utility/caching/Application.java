package org.shahin.nazarov.utility.caching;

import org.shahin.nazarov.utility.caching.memcached.Memcached;
import org.shahin.nazarov.utility.caching.memcached.config.MemcachedConfig;

public class Application {

    public static void main(String[] args) {
        try(Memcached memcached = Memcached.getMemcached(new MemcachedConfig.Builder().build())){
            memcached.getMemcachedClient().append("ee","55");
            System.out.println(memcached.getMemcachedClient().get("ee"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
