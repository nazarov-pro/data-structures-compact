package org.shahin.nazarov.questions.drivingcars;

import lombok.Data;

@Data
public class Car {
    private Line line;
    private String name;
    private int speed;
    private int size;
    private Position position;
    private int id;

    public boolean start(Area area) {
        id = (name.hashCode() % (Integer.MAX_VALUE - 1)) + 1;
        position = line.getStart();
        if(!area.go(this)){
            position = null;
            return false;
        }
        return true;
    }

    public boolean go(Area area) {
        // it means it has to start at the beginning of the line
        if(position == null){
            throw new RuntimeException("You have to start the car first !");
        }
        int next;
        Position tmpPosition = position;

        if (line.isHorizontal()) {
            next = position.getY() + speed;
            if (next + size > line.getStop().getY()) {
//                next = (next + size) % line.getStop().getY();
                next = 0;
            }
            position.setY(next);
        } else {
            next = position.getX() + speed;
            if (next + size > line.getStop().getX()) {
//                next = (next + size) % line.getStop().getX();
                next = 0;
            }
            position.setX(next);
        }
        if(!area.go(this)){
            position = tmpPosition;
            return false;
        }
        return true;
    }
}
