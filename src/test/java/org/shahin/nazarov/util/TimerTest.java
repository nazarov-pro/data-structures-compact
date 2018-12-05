package org.shahin.nazarov.util;

import org.junit.Test;

public class TimerTest {
    @Test
    public void test() throws InterruptedException {
        Timer timer = new Timer();
        timer.start();

        Thread.sleep(1000);

        timer.stop();
    }
}
