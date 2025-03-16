package org.vw.infrastructure;

import org.vw.domain.CleaningRobot;
import org.vw.domain.Grid;

public class GridVisualizer {
    public static void printGrid(Grid grid) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int y = grid.getMaxY(); y >= 0; y--) {
            System.out.print(" " + ((y < grid.getMaxY()) ? y : "-") + " ");
            for (int x = 0; x < grid.getMaxX(); x++) {
                if (y == grid.getMaxY()) {
                    System.out.print(" " + x + "  ");
                    continue;
                }
                String cellContent = "  ";
                for (int robotIndex = 0; robotIndex < grid.getRobots().size(); robotIndex++) {
                    CleaningRobot robot = grid.getRobots().get(robotIndex);
                    if (robot.getPosition().getX() == x && robot.getPosition().getY() == y) {
                        cellContent = (robotIndex + 1) + "" + robot.getPosition().getDirection();
                        break;
                    }
                }
                System.out.print("[" + cellContent + "]");
            }
            System.out.println();
        }
        System.out.println();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
