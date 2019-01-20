package org.shaheen.nazarov.server.util;

import static org.shaheen.nazarov.server.util.ServerConstants.MANAGEMENT_NOTIFICATION_HELPER;

public class ShutdownHook extends Thread {

    @Override
    public void run() {
        ServerProperty serverProperty = ServerPropertySingleton.getInstance();
        System.out.println("Go to die " + serverProperty.getName());
        if (serverProperty.getServerManagementEndpoint() != null)
            MANAGEMENT_NOTIFICATION_HELPER.stop();
    }
}
