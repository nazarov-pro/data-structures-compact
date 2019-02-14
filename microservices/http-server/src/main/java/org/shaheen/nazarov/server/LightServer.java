package org.shaheen.nazarov.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.*;
import lombok.Getter;
import org.shaheen.nazarov.server.domain.ServerStatus;
import org.shaheen.nazarov.server.handlers.HealthHandler;
import org.shaheen.nazarov.server.security.ApiKeyAuthenticator;
import org.shaheen.nazarov.server.security.BasicAuthenticator;
import org.shaheen.nazarov.server.security.CerificateAuthenticator;
import org.shaheen.nazarov.server.util.*;

import javax.swing.text.DocumentFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class LightServer {
    private HttpServer httpServer;
    @Getter
    private ServerProperty serverProperty;
    @Getter
    private List<String> contexts;
    @Getter
    private LocalDateTime startedTime;

    private Authenticator authenticator;

    private static LightServer server;

    private LightServer(Builder builder) {
        this.httpServer = builder.httpServer;
        this.serverProperty = builder.serverProperty;
        this.contexts = builder.contexts;
        this.authenticator = builder.authenticator;
        server = this;
    }

    public static LightServer getServer() {
        if (server == null) {
            throw new RuntimeException(ServerConstants.EXCEPTION_SERVER_NOT_INITIALIZED);
        }
        return server;
    }

    public void start() {
        if (httpServer == null) {
            throw new RuntimeException(ServerConstants.EXCEPTION_SERVER_NOT_INITIALIZED);
        }
        startedTime = LocalDateTime.now();
        httpServer.start();
        if (serverProperty.getServerManagementEndpoint() != null)
            MANAGEMENT_NOTIFICATION_HELPER.start();
        ServerStatus serverStatus = new ServerStatus();
        serverStatus.setName(serverProperty.getName());
        serverStatus.setPort(serverProperty.getPort());
        serverStatus.setHost(serverProperty.getHost());
        serverStatus.setStatus(STATUS_UP);
        try {
            System.out.printf(JSON.writeValueAsString(serverStatus) + "\n");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int sizeRunningThreads() {
        if (httpServer.getExecutor() instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) httpServer.getExecutor();
            return executor.getActiveCount();
        }
        return -1;
    }

    public void stop(int code) {
        if (httpServer == null) {
            System.err.println(ServerConstants.EXCEPTION_SERVER_NOT_INITIALIZED);
            return;
        }
        httpServer.stop(code);
        if (code == 0) {
            System.exit(code);
        }
        System.out.printf(ServerConstants.INFO_SERVER_STOPPED,
                serverProperty.getName(),
                serverProperty.getPort());
    }

    public void restart() {
        stop(0);
        start();
    }

    public void add(String path, HttpHandler handler) {
        HttpContext context = httpServer.createContext(path, handler);

        if(authenticator != null){
            context.setAuthenticator(authenticator);
        }
        contexts.add(path);
    }

    public void add(String path, HttpHandler handler, Filter filter) {
        HttpContext context = httpServer.createContext(path, handler);
        context.getFilters().add(filter);
        if(authenticator != null){
            context.setAuthenticator(authenticator);
        }
        contexts.add(path);
    }

    public void remove(String path, HttpHandler handler) {
        httpServer.removeContext(path);
        contexts.remove(path);
    }

    public static class Builder {
        private HttpServer httpServer;
        private ServerProperty serverProperty;
        private List<String> contexts = new ArrayList<>();
        private Authenticator authenticator;

        public Builder initialize() throws IOException {
            if (this.httpServer != null) {
                System.err.println(ServerConstants.EXCEPTION_SERVER_ALREADY_INITIALIZED);
            }

            this.serverProperty = ServerPropertySingleton.getInstance();
            this.httpServer = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(serverProperty.getPort())), 0);

            switch (serverProperty.getThreadPoolSize()) {
                case "*":
                    this.httpServer.setExecutor(Executors.newCachedThreadPool());
                    break;
                case "1":
                    this.httpServer.setExecutor(Executors.newSingleThreadExecutor());
                    break;
                default:
                    this.httpServer.setExecutor(Executors.newFixedThreadPool
                            (Integer.parseInt(serverProperty.getThreadPoolSize())));
                    break;
            }

            switch (serverProperty.getAuth()){
                case PROPERTY_DEFAULT_AUTH_BASIC:
                    authenticator = new BasicAuthenticator();
                    break;
                case PROPERTY_DEFAULT_API_KEY:
                    authenticator = new ApiKeyAuthenticator();
                    break;
                case PROPERTY_DEFAULT_AUTH_CERIFICATE:
                    authenticator = new CerificateAuthenticator();
                    break;
            }
            return this;
        }

        public Builder addHealth(String key) {
            contexts.add(PATH_HEALTH);
            if(authenticator == null){
                httpServer.createContext(PATH_HEALTH, new HealthHandler());
            } else {
                httpServer.createContext(PATH_HEALTH, new HealthHandler()).setAuthenticator(authenticator);
            }
            serverProperty.setHealthKey(key);
            return this;
        }

        public Builder addServerManagement(String endpoint) {
            serverProperty.setServerManagementEndpoint(endpoint.startsWith("http") ?
                    endpoint : ("http://" + endpoint));
            Runtime.getRuntime().addShutdownHook(new ShutdownHookManagement());
            return this;
        }

        public Builder addShutdownHook(Runnable shutdown) {
            Runtime.getRuntime().addShutdownHook(new ShutdownHook(shutdown));
            return this;
        }

        public Builder add(String path, HttpHandler handler) {
            HttpContext context = httpServer.createContext(path, handler);

            if(authenticator != null){
                context.setAuthenticator(authenticator);
            }
            contexts.add(path);
            return this;
        }

        public Builder add(String path, HttpHandler handler, Filter filter) {
            HttpContext context = httpServer.createContext(path, handler);
            context.getFilters().add(filter);

            if(authenticator != null){
                context.setAuthenticator(authenticator);
            }

            contexts.add(path);
            return this;
        }

        public LightServer build() {
            return new LightServer(this);
        }
    }

}
