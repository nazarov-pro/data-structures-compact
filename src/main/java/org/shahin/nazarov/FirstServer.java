package org.shahin.nazarov;

import org.shaheen.nazarov.server.LightServer;

import java.io.IOException;

public class FirstServer {
    public static void main(String[] args) throws IOException {
            LightServer server = new LightServer.Builder().initialize().addHealth("dsf")
                    .addServerManagement("localhost:1111").build();
            server.start();
    }
}
