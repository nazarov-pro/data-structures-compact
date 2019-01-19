package org.shahin.nazarov.questions.drivingcars;

public class Area {
    private static int[][] map = new int[100][100];

    public synchronized boolean go(Car car) {
        Line currentPosition = getOldPosition(car.getId());
        Line newPostition = getNewPosition(car.getPosition(), car.getSize(), car.getLine().isHorizontal());
        if (!checkAvaliable(newPostition, car.getId())) {
            return false;
        }
        blockPosition(newPostition, car.getId());
        if (currentPosition.getStart() != null) {
            blockPosition(currentPosition, 0);
        }
        return true;
    }

    private Line getOldPosition(int id) {
        Line line = new Line();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == id) {
                    if (line.getStart() == null) {
                        line.setStart(new Position(i, j));
                        line.setStop(line.getStart());
                    } else {
                        line.setStop(new Position(i, j));
                    }
                }
            }
        }
        return line;
    }

    private void blockPosition(Line line, int id) {
        if (line.isHorizontal()) {
            for (int i = line.getStart().getY(); i <= line.getStop().getY(); i++) {
                map[line.getStart().getX()][i] = id;
            }
        } else {
            for (int i = line.getStart().getX(); i <= line.getStop().getX(); i++) {
                map[i][line.getStart().getY()] = id;
            }
        }
    }

    private Line getNewPosition(Position position,
                                int size,
                                boolean horizontal) {
        Line line = new Line();
        if (horizontal) {
            line.setStart(new Position(position.getX(), position.getY() + size));
        } else {
            line.setStart(new Position(position.getX() + size, position.getY()));
        }
        line.setStop(line.getStart());
        return line;
    }

    private boolean checkAvaliable(Line line, int id) {
        if (line.isHorizontal()) {
            for (int i = line.getStart().getY(); i <= line.getStop().getY(); i++) {
                if (!(map[line.getStart().getX()][i] == 0 || map[line.getStart().getX()][i] == id)) {
                    return false;
                }
            }
        } else {
            for (int i = line.getStart().getX(); i <= line.getStop().getX(); i++) {
                if (!(map[i][line.getStart().getY()] == 0 || map[i][line.getStart().getY()] == id)) {
                    return false;
                }
            }
        }
        return true;
    }
}
