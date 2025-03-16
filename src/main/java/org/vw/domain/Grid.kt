package org.vw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Grid {
    private final int maxX;
    private final int maxY;
    private List<CleaningRobot> robots;

    public Grid(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        robots = new ArrayList<>();
    }

    public void addRobot(CleaningRobot robot) {
        robots.add(robot);
    }

    public boolean isCellAvailable(int x, int y) {
        return this.isInsideGrid(x, y) && !this.isCellOccupied(x, y);
    }

    private boolean isInsideGrid(int newX, int newY) {
        return newX >= 0 && newX < maxX && newY >= 0 && newY < maxY;
    }

    private boolean isCellOccupied(int x, int y) {
        for (CleaningRobot robot : robots) {
            if (robot.getPosition().getX() == x && robot.getPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }
}
