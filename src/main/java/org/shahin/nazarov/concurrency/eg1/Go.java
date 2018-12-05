package org.shahin.nazarov.concurrency.eg1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Go implements Runnable {

    private Car car;
    private Area area;

    @Override
    public void run() {
        try {
            while (!car.start(area)) {
                System.out.println(car.getName() + " impossible to start");
                Thread.sleep(100);
            }
            System.out.println(car.getName() + " started -> " + car.getPosition());
            while (true) {
                Thread.sleep(100);
                if(car.go(area)){
                    System.out.println(car.getName() + " -> " + car.getPosition());
                }else {
                    System.out.println(car.getName() + " waiting -> " + car.getPosition());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
