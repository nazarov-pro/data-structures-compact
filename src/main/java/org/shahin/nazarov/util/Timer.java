package org.shahin.nazarov.util;

public class Timer {

    private long timeMillis = 0L;

    public void start() {
        System.out.println("Timer started");
        timeMillis = System.currentTimeMillis();
    }

    public void stop() {
        System.out.printf("Timer stopped millis %d \n", timeMillis - System.currentTimeMillis());
        timeMillis = 0L;
    }

}
