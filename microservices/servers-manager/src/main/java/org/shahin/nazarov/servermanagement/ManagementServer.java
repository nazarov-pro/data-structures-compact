package org.shahin.nazarov.servermanagement;

import org.shaheen.nazarov.server.LightServer;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shahin.nazarov.servermanagement.dao.ServerDao;
import org.shahin.nazarov.servermanagement.handlers.HandshakeHandler;
import org.shahin.nazarov.servermanagement.handlers.ServerHandler;
import org.shahin.nazarov.servermanagement.model.Server;
import org.shahin.nazarov.servermanagement.util.ManagementConstants;
import org.shahin.nazarov.servermanagement.util.ProcessStart;
import org.shahin.nazarov.servermanagement.util.ServerUtil;

import java.io.IOException;

public class ManagementServer {
    public static void main(String[] args) throws IOException {
        LightServer lightServer = new LightServer.Builder().initialize().addHealth()
                .add(ManagementConstants.PATH_SERVERS, new ServerHandler())
                .add(ServerConstants.PATH_MANAGEMENT_HANDSHAKE, new HandshakeHandler())
                .build();
        lightServer.start();
        ServerDao serverDao = new ServerDao();
        ServerUtil serverUtil = new ServerUtil();
        serverUtil.jarFiles().stream().forEach(j -> {
            Server server = new Server();
            server.setFileName(j.getName());
            server.setActive(j.isOpen());
            serverDao.add(server);
            if (!j.isOpen()) {
                try {
                    String output = new ProcessStart().run(ManagementConstants.path, j.getName(),
                            "-Xmx" + "100" + "m");
                    System.out.println(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        }
    }
