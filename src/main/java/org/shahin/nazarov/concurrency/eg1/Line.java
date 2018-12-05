package org.shahin.nazarov.concurrency.eg1;


import lombok.Data;

@Data
public class Line {
    private Position start;
    private Position stop;
    private boolean horizontal = false;

    public void setStop(Position position) {
        this.stop = position;
        if (this.start.getX() == this.getStop().getX()) {
            this.horizontal = true;
        }
    }

}
