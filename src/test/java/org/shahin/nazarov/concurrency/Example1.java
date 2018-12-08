package org.shahin.nazarov.concurrency;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shahin.nazarov.concurrency.eg1.Area;
import org.shahin.nazarov.concurrency.eg1.Car;
import org.shahin.nazarov.concurrency.eg1.Go;
import org.shahin.nazarov.concurrency.eg1.RoadBuilder;

public class Example1 {
    public static void main(String[] args) {
        Area area = new Area();

        Car car1 = new Car();
        car1.setLine(new RoadBuilder().getVerticalRoad());
        car1.setSize(2);
        car1.setSpeed(2);
        car1.setName("F1 Car");

        Thread thread = new Thread(new Go(car1, area), car1.getName());
        thread.start();

        Car car2 = new Car();
        car2.setLine(new RoadBuilder().getHorizontalRoad());
        car2.setSize(1);
        car2.setSpeed(1);
        car2.setName("Regular Car");

        Thread thread2 = new Thread(new Go(car2, area), car2.getName());
        thread2.start();
    }
}
