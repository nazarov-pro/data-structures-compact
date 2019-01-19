package org.shahin.nazarov.questions;

import org.shahin.nazarov.questions.drivingcars.*;

public class DrivingCars {
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
