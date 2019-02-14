package org.shaheen.nazarov.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class ServerPropertySingleton {

    public static ServerProperty serverProperty;
    public ServerUtil serverUtil = new ServerUtil();

    public static ServerProperty getInstance() {
        if (serverProperty == null) {
            serverProperty = new ServerPropertySingleton().getServerProperties();
        }
        return serverProperty;
    }

    private ServerPropertySingleton() {

    }

    private ServerProperty getServerProperties() {
        ServerProperty serverProperty = new ServerProperty();
        try {
            Properties properties =
                    readProperties(this.getClass().getClassLoader()
                            .getResourceAsStream(PATH_SERVER_PROPERTIES));

            serverProperty.setName(properties.getProperty(PROPERTY_KEY_NAME, PROPERTY_DEFAULT_NAME));

            serverProperty.setJarFileName(properties.getProperty(PROPERTY_KEY_JAR_NAME, PROPERTY_DEFAULT_JAR_NAME) + ".jar");

            serverProperty.setThreadPoolSize(properties.getProperty(PROPERTY_KEY_THREAD_POOL_SIZE,
                    PROPERTY_DEFAULT_THREAD_POOL_SIZE));

            serverProperty.setPort(properties.getProperty(PROPERTY_KEY_PORT,
                    PROPERTY_DEFAULT_PORT));

            serverProperty.setHost(serverUtil.getIpAddress());
            serverProperty.setAuth(properties.getProperty(PROPERTY_KEY_AUTH, PROPERTY_DEFAULT_AUTH));

            switch (serverProperty.getAuth()) {
                case PROPERTY_DEFAULT_AUTH_NONE:
                    return serverProperty;
                case PROPERTY_DEFAULT_AUTH_BASIC:
                    serverProperty.setPassword(properties.getProperty(PROPERTY_KEY_AUTH_PASSWORD));
                    serverProperty.setUsername(properties.getProperty(PROPERTY_KEY_AUTH_USERNAME));
                    break;
                case PROPERTY_DEFAULT_API_KEY:
                    serverProperty.setToken(properties.getProperty(PROPERTY_KEY_AUTH_API_KEY));
                    break;
                case PROPERTY_DEFAULT_AUTH_CERIFICATE:
                    serverProperty.setCertificate(properties.getProperty(PROPERTY_KEY_AUTH_PUBLIC_CERTIFICATE));
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format(EXCEPTION_UNSUPPORTED_AUTH,
                                    serverProperty.getAuth()));
            }

            serverProperty.setPath(properties.getProperty(PROPERTY_KEY_AUTH_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverProperty;
    }

    private Properties readProperties(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
