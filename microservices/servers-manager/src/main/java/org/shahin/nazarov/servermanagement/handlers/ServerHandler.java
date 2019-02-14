package org.shahin.nazarov.servermanagement.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.shaheen.nazarov.server.domain.ServerStatus;
import org.shaheen.nazarov.server.handlers.AbstractSecuredHandler;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shahin.nazarov.servermanagement.dao.ServerDao;
import org.shahin.nazarov.servermanagement.model.JarFile;
import org.shahin.nazarov.servermanagement.model.Server;
import org.shahin.nazarov.servermanagement.model.ServerResponse;
import org.shahin.nazarov.servermanagement.model.StartRequest;
import org.shahin.nazarov.servermanagement.util.ManagementConstants;
import org.shahin.nazarov.servermanagement.util.ProcessStart;
import org.shahin.nazarov.servermanagement.util.ServerUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class ServerHandler extends AbstractSecuredHandler {

    private ProcessStart processStart = new ProcessStart();
    private ServerUtil serverUtil = new ServerUtil();
    private ServerDao serverDao = new ServerDao();

    @Override
    public void successHandler(HttpExchange httpExchange) throws IOException {
        String response = "";
        String requestURI = httpExchange.getRequestURI().toString();
        String contentType = httpExchange.getRequestHeaders().getFirst(HEADER_CONTENT_TYPE);
        int responseCode = 200;
        if (requestURI.equals(ManagementConstants.PATH_SERVERS_JAR) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_GET)) {
            response = JSON.writeValueAsString(serverUtil.jarFiles());
        } else if (requestURI.equals(ManagementConstants.PATH_SERVERS) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_GET)) {
            response = JSON.writeValueAsString(serverDao.list().toArray());
        } else if (requestURI.equals(ManagementConstants.PATH_SERVERS_MANAGEMENT) &&
                httpExchange.getRequestMethod().equals(REQUEST_METHOD_POST) &&
                contentType.contains(HEADER_CONTENT_TYPE_JSON)) {
            StartRequest request = JSON.readValue(httpExchange.getRequestBody(), StartRequest.class);
            ServerResponse serverResponse = new ServerResponse();
            Optional<JarFile> target = serverUtil.jarFiles().stream().filter(j ->
                    j.getName().equals(request.getFileName())).findFirst();
            if (target.isPresent()) {
                JarFile jarFile = target.get();
                if (jarFile.isOpen())
                    serverResponse.setDescription("N/A");
                else {
                    String output = processStart.run(ManagementConstants.path, jarFile.getName(),
                            "-Xmx" + request.getMaxHeapMb() + "m");
                    serverResponse.setDescription(output);
                }
            } else {
                responseCode = RESPONSE_CODE_NOT_FOUND;
                serverResponse.setDescription(PAGE_NOT_FOUND_TEXT);
            }
            response = JSON.writeValueAsString(serverResponse);
        } else {
            responseCode = RESPONSE_CODE_NOT_FOUND;
        }
        httpExchange.sendResponseHeaders(responseCode, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
