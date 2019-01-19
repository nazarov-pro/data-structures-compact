package org.shahin.nazarov.servermanagement;

import org.shaheen.nazarov.server.LightServer;
import org.shahin.nazarov.servermanagement.handlers.ServerHandler;

import java.io.IOException;

public class ManagementServer {
    public static void main(String[] args) throws IOException {
        LightServer lightServer = new LightServer.Builder().initialize().addHealth()
                .add("/servers", new ServerHandler()).build();
        lightServer.start();
    }
}
