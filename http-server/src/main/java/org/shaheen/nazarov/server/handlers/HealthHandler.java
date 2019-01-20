package org.shaheen.nazarov.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.shaheen.nazarov.server.LightServer;
import org.shaheen.nazarov.server.domain.HealthCheck;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class HealthHandler extends AbstractSecuredHandler {

    private static HealthCheck healthCheck = new HealthCheck();

    @Override
    public void successHandler(HttpExchange httpExchange) throws IOException {
        URI requestURI = httpExchange.getRequestURI();
        LightServer server = LightServer.getServer();
        String response = "";
        if (requestURI.toString().equals(PATH_HEALTH_SHUTDOWN) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_POST)) {
            server.stop(0);
        } else if (requestURI.toString().equals(PATH_HEALTH) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_GET)) {
            healthCheck.setContexts(server.getContexts());
            healthCheck.setPath(requestURI.toString());
            healthCheck.setPort(server.getServerProperty().getPort());
            healthCheck.setTime(LocalDateTime.now().toString());
            healthCheck.setStartedTime(server.getStartedTime().toString());
            healthCheck.setRunningThreads(server.sizeRunningThreads());
            healthCheck.setMaxThreads(server.getServerProperty().getThreadPoolSize());
            healthCheck.setHeapCurrent(formatSize(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            healthCheck.setHeapMax(formatSize(Runtime.getRuntime().maxMemory()));
            response = JSON.writeValueAsString(healthCheck);
        } else {
            printNotFound(httpExchange);
            return;
        }

        printOk(httpExchange, response);
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }
}
