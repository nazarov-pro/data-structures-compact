package org.shahin.nazarov.utility.caching.memcached;

import lombok.Getter;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import org.shahin.nazarov.utility.caching.memcached.config.MemcachedConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Memcached implements AutoCloseable {
    private static Memcached memcached;
    @Getter
    private final MemcachedClient memcachedClient;

    private Memcached(MemcachedConfig config) {
        ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder()
                .setProtocol(config.getProtocol());

        if (config.isAuth()) {
            AuthDescriptor ad = new AuthDescriptor(new String[]{"PLAIN"},
                    new PlainCallbackHandler(config.getUsername(), config.getPassword()));
            connectionFactoryBuilder.setAuthDescriptor(ad);
        }

        try {
            this.memcachedClient = new MemcachedClient(connectionFactoryBuilder.build(),
                    AddrUtil.getAddresses(config.getAddresses()));
            return;
        } catch (IOException e) {
            // handle exception
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public void close() throws Exception {
        if(memcachedClient != null) memcachedClient.shutdown(10, TimeUnit.SECONDS);
    }

    public static Memcached getMemcached(MemcachedConfig config) {
        if (memcached == null)
            memcached = new Memcached(config);
        return memcached;
    }
}
