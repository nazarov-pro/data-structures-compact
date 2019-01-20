package org.shahin.nazarov.servermanagement.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.shaheen.nazarov.server.domain.Handshake;
import org.shaheen.nazarov.server.handlers.AbstractSecuredHandler;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shahin.nazarov.servermanagement.dao.ServerDao;

import java.io.IOException;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class HandshakeHandler extends AbstractSecuredHandler {
    private ServerDao serverDao = new ServerDao();

    @Override
    public void successHandler(HttpExchange httpExchange) throws IOException {
        String requestURI = httpExchange.getRequestURI().toString();
        String contentType = httpExchange.getRequestHeaders().getFirst(ServerConstants.HEADER_CONTENT_TYPE);
        if (requestURI.equals(PATH_MANAGEMENT_HANDSHAKE) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_POST) &&
                contentType.contains(HEADER_CONTENT_TYPE_JSON)) {
            Handshake request = JSON.readValue(httpExchange.getRequestBody(),
                    Handshake.class);
            serverDao.get(request.getJarFileName())
                    .ifPresent(server -> {
                        server.setActive(request.getStatus().equals(STATUS_UP));
                        server.setPort(request.getPort());
                        server.setHost(request.getHost());
                        server.setLabel(request.getName());
                        serverDao.update(server, request.getJarFileName());
                    });
            printOk(httpExchange, STATUS_OK);
            return;
        }
        printNotFound(httpExchange);
    }
}
