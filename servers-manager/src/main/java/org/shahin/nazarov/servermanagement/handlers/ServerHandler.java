package org.shahin.nazarov.servermanagement.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.shaheen.nazarov.server.domain.ServerStatus;
import org.shaheen.nazarov.server.handlers.AbstractSecuredHandler;
import org.shahin.nazarov.servermanagement.model.JarFile;
import org.shahin.nazarov.servermanagement.model.Server;
import org.shahin.nazarov.servermanagement.model.ServerResponse;
import org.shahin.nazarov.servermanagement.model.StartRequest;
import org.shahin.nazarov.servermanagement.util.ProcessStart;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ServerHandler extends AbstractSecuredHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Path path = Paths.get("C:\\Users\\s0552\\projects\\project_best\\training\\servers-manager\\libs");
    private static Set<Server> servers = new HashSet<>();
    private ProcessStart processStart = new ProcessStart();

    @Override
    public void successHandler(HttpExchange httpExchange) throws IOException {
        String requestURI = httpExchange.getRequestURI().toString();
        String response = "";
        String contentType = httpExchange.getRequestHeaders().getFirst("Content-Type");
        int responseCode = 200;
        if (requestURI.equals("/servers/jars") && httpExchange.getRequestMethod().equals("GET")) {
            response = objectMapper.writeValueAsString(jarFiles());
        } else if (requestURI.equals("/servers") && httpExchange.getRequestMethod().equals("GET")) {
            response = objectMapper.writeValueAsString(servers);
        } else if (requestURI.equals("/servers/start") &&
                httpExchange.getRequestMethod().equals("POST") &&
                contentType.contains("application/json")) {
            StartRequest request = objectMapper.readValue(httpExchange.getRequestBody(), StartRequest.class);
            ServerResponse serverResponse = new ServerResponse();
            Optional<JarFile> target = jarFiles().stream().filter(j ->
                    j.getName().equals(request.getFileName())).findFirst();
            if (target.isPresent()) {
                JarFile jarFile = target.get();
                if (jarFile.isOpen())
                    serverResponse.setDescription("N/A");
                else {
                    String output = processStart.run(path, jarFile.getName(),
                            "-Xmx" + request.getMaxHeapMb() + "m");
                    ServerStatus serverStatus = objectMapper.readValue(output, ServerStatus.class);
                    Server server = new Server();
                    server.setFileName(request.getFileName());
                    server.setMaxHeapMb(request.getMaxHeapMb());

                    server.setActive(serverStatus.getStatus().equals("UP"));
                    server.setLabel(serverStatus.getName());
                    server.setPath("http://localhost:" + serverStatus.getPort() + "/health");
                    server.setPort(serverStatus.getPort());
                    servers.add(server);
                    serverResponse.setDescription(serverStatus.getStatus());
                }
            } else {
                responseCode = 404;
                serverResponse.setDescription("notFound");
            }
            response = objectMapper.writeValueAsString(serverResponse);
        } else {
            responseCode = 404;
        }
        httpExchange.sendResponseHeaders(responseCode, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private List<JarFile> jarFiles() throws IOException {
        List<JarFile> jarFileList = new ArrayList<>();
        Files.list(path).filter(p -> p.getFileName().toString().endsWith(".jar")).forEach(p -> {
            JarFile jarFile = new JarFile();
            jarFile.setName(p.getFileName().toString());
            try {
                Files.move(p, p, StandardCopyOption.ATOMIC_MOVE);
                jarFile.setOpen(false);
            } catch (IOException e) {
                jarFile.setOpen(true);
            }

            try {
                jarFile.setModified(Files.getLastModifiedTime(p).toString());
            } catch (IOException e) {
                jarFile.setModified("N/A");
            }
            jarFileList.add(jarFile);
        });
        return jarFileList;
    }
}
