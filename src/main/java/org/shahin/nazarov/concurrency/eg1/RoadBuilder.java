package org.shahin.nazarov.concurrency.eg1;

import static org.shahin.nazarov.concurrency.eg1.Data.*;

public class RoadBuilder {

    public Line getHorizontalRoad(){
        Line line = new Line();
        line.setStart(horizontalStart);
        line.setStop(horizontalStop);
        return line;
    }

    public Line getVerticalRoad(){
        Line line = new Line();
        line.setStart(verticalStart);
        line.setStop(verticalStop);
        return line;
    }
}
