package org.shahin.nazarov.utility.caching.memcached.config;

import lombok.Getter;
import net.spy.memcached.ConnectionFactoryBuilder;

import java.util.Arrays;
import java.util.List;

@Getter
public class MemcachedConfig {
    private String username;
    private String password;
    private boolean auth;
    private List<String> addresses;
    private ConnectionFactoryBuilder.Protocol protocol;

    private MemcachedConfig(String username, String password, boolean auth, List<String> addresses,
                            ConnectionFactoryBuilder.Protocol protocol) {
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.addresses = addresses;
        this.protocol = protocol;
    }

    public static final class Builder {

        private String username;
        private String password;
        private boolean auth = false;
        private List<String> addresses = Arrays.asList("127.0.0.1:11211");
        private ConnectionFactoryBuilder.Protocol protocol = ConnectionFactoryBuilder.Protocol.BINARY;

        public Builder auth(final String username, final String password) {
            this.username = username;
            this.password = password;
            this.auth = true;
            return this;
        }

        public Builder subscribe(String... addresses) {
            this.addresses = Arrays.asList(addresses);
            return this;
        }

        public Builder protocol(ConnectionFactoryBuilder.Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public MemcachedConfig build() {
            return new MemcachedConfig(username, password, auth, addresses, protocol);
        }


    }
}
