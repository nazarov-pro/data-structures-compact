package org.shaheen.nazarov.server.util;

import static org.shaheen.nazarov.server.util.ServerConstants.MANAGEMENT_NOTIFICATION_HELPER;

public class ShutdownHook extends Thread {

    private Runnable runnable;


    public ShutdownHook(Runnable runnable){
        super();
        this.runnable = runnable;
    }


    @Override
    public void run() {
        if(runnable != null)
            runnable.run();
    }
}
