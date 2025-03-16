package org.vw.application;

import org.vw.domain.CleaningRobot;
import org.vw.domain.Grid;
import org.vw.infrastructure.GridVisualizer;
import org.vw.infrastructure.RobotMovementsFileReader;

public class RobotController {

    public static void main(String[] args) {
        boolean showGridOnMovements = false;
        String filePath = "input.txt";
        for (String arg : args) {
            if (arg.equals("--show-grid")) {
                showGridOnMovements = true;
            } else {
                filePath = arg;
            }
        }

        Grid grid = readFile(filePath);
        if (grid == null) {
            System.out.println("Error: Grid could not be loaded.");
            return;
        }
        execMovements(grid, showGridOnMovements);
        consoleOutput(grid);
    }

    private static Grid readFile(String filePath){
        RobotMovementsFileReader fileReader = new RobotMovementsFileReader(filePath);
        return fileReader.loadFileContents();
    }

    private static void execMovements(Grid grid, Boolean showGrid) {
        for (CleaningRobot robot: grid.getRobots()) {
            for (char command : robot.getInstructions()) {
                robot.executeCommand(command);

                if (showGrid) {
                    GridVisualizer.printGrid(grid);
                }
            }
        }
    }

    private static void consoleOutput(Grid grid) {
        for (CleaningRobot robot: grid.getRobots()) {
            System.out.println(robot.getPosition());
        }
    }
}
