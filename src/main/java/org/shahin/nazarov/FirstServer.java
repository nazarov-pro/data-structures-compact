package org.shahin.nazarov;

import org.shaheen.nazarov.server.LightServer;

import java.io.IOException;

public class FirstServer {
    public static void main(String[] args) {
        try {
            LightServer server = new LightServer.Builder().initialize().addHealth().build();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
