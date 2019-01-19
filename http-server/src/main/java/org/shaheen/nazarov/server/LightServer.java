package org.shaheen.nazarov.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import org.shaheen.nazarov.server.domain.ServerStatus;
import org.shaheen.nazarov.server.handlers.HealthHandler;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shaheen.nazarov.server.util.ServerProperty;
import org.shaheen.nazarov.server.util.ServerPropertySingleton;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.shaheen.nazarov.server.util.ServerConstants.PATH_HEALTH;
import static org.shaheen.nazarov.server.util.ServerConstants.STATUS_UP;

public class LightServer {
    private HttpServer httpServer;
    @Getter
    private ServerProperty serverProperty;
    @Getter
    private List<String> contexts;
    @Getter
    private LocalDateTime startedTime;

    private static LightServer server;

    private LightServer(Builder builder) {
        this.httpServer = builder.httpServer;
        this.serverProperty = builder.serverProperty;
        this.contexts = builder.contexts;
        server = this;
    }

    public static LightServer getServer() {
        if (server == null) {
            System.err.println(ServerConstants.EXCEPTION_SERVER_NOT_INITIALIZED);
        }
        return server;
    }

    public void start() {
        if (httpServer == null) {
            System.err.println(ServerConstants.EXCEPTION_SERVER_NOT_INITIALIZED);
            return;
        }
        startedTime = LocalDateTime.now();
        httpServer.start();
        ServerStatus serverStatus = new ServerStatus();
        serverStatus.setName(serverProperty.getName());
        serverStatus.setPort(serverProperty.getPort());
        serverStatus.setPath(httpServer.getAddress().getHostName() + ":" + serverStatus.getPort());
        serverStatus.setStatus(STATUS_UP);
        try {
            System.out.printf(HealthHandler.objectMapper.writeValueAsString(serverStatus) + "\n");
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
        httpServer.createContext(path, handler);
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

        public Builder initialize() throws IOException {
            if (this.httpServer != null) {
                System.err.println(ServerConstants.EXCEPTION_SERVER_ALREADY_INITIALIZED);
            }
            this.serverProperty = ServerPropertySingleton.getInstance();
            this.httpServer = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(serverProperty.getPort())), 0);
            if (serverProperty.getThreadPoolSize().equals("*")) {
                this.httpServer.setExecutor(Executors.newCachedThreadPool());
            } else {
                this.httpServer.setExecutor(Executors.newFixedThreadPool
                        (Integer.parseInt(serverProperty.getThreadPoolSize())));
            }
            return this;
        }

        public Builder addHealth() {
            httpServer.createContext(PATH_HEALTH, new HealthHandler());
            contexts.add(PATH_HEALTH);
            return this;
        }

        public Builder add(String path, HttpHandler handler) {
            httpServer.createContext(path, handler);
            contexts.add(path);
            return this;
        }

        public LightServer build() {
            return new LightServer(this);
        }
    }

}
