package org.shahin.nazarov.questions.drivingcars;


import static org.shahin.nazarov.questions.drivingcars.Data.*;

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
