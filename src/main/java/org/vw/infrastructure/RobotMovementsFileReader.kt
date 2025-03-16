package org.vw.infrastructure;

import org.vw.domain.CleaningRobot;
import org.vw.domain.Grid;
import org.vw.domain.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RobotMovementsFileReader {
    private final String filePath;

    public RobotMovementsFileReader(String filePath) {
        this.filePath = filePath;
    }

    public Grid loadFileContents() {
        Scanner scanner = null;
        Grid grid = null;

        try {
            File inputFile = new File(filePath);

            System.out.println("Reading input file: " + filePath);
            scanner = new Scanner(inputFile);

            int gridX = scanner.nextInt();
            int gridY = scanner.nextInt();
            grid = new Grid(gridX + 1, gridY + 1);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Position.Direction direction = Position.Direction.valueOf(scanner.next());
                scanner.nextLine();

                if (!scanner.hasNextLine()) break;
                String commands = scanner.nextLine();

                CleaningRobot robot = new CleaningRobot(new Position(grid, x, y, direction), commands.toCharArray());
                grid.addRobot(robot);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } finally {
            if (scanner != null) scanner.close();
        }
        return grid;
    }
}
