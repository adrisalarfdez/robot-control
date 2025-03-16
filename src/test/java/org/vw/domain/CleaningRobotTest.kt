package org.vw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CleaningRobotTest {
    private Grid grid;
    private CleaningRobot robot;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5);
        Position startPosition = new Position(grid, 2, 2, Position.Direction.N);
        robot = new CleaningRobot(startPosition, new char[]{'M', 'L', 'M', 'R', 'M'});
    }

    @Test
    void testExecuteCommand_MoveForward() {
        robot.executeCommand('M');
        assertEquals(2, robot.getPosition().getX());
        assertEquals(3, robot.getPosition().getY());
        assertEquals(Position.Direction.N, robot.getPosition().getDirection());
    }

    @Test
    void testExecuteCommand_RotateLeft() {
        robot.executeCommand('L');
        assertEquals(Position.Direction.W, robot.getPosition().getDirection());
    }

    @Test
    void testExecuteCommand_RotateRight() {
        robot.executeCommand('R');
        assertEquals(Position.Direction.E, robot.getPosition().getDirection());
    }

    @Test
    void testExecuteCommand_EastBlocked() {
        robot.getPosition().setX(grid.getMaxX());
        robot.getPosition().setDirection(Position.Direction.E);
        robot.executeCommand('M');
        assertEquals(grid.getMaxX(), robot.getPosition().getX());
    }

    @Test
    void testExecuteCommand_NorthBlocked() {
        robot.getPosition().setY(grid.getMaxY());
        robot.getPosition().setDirection(Position.Direction.N);
        robot.executeCommand('M');
        assertEquals(grid.getMaxY(), robot.getPosition().getY());
    }

    @Test
    void testExecuteCommand_SouthBlocked() {
        robot.getPosition().setY(0);
        robot.getPosition().setDirection(Position.Direction.S);
        robot.executeCommand('M');
        assertEquals(0, robot.getPosition().getY());
    }

    @Test
    void testExecuteCommand_WestBlocked() {
        robot.getPosition().setX(0);
        robot.getPosition().setDirection(Position.Direction.W);
        robot.executeCommand('M');
        assertEquals(0, robot.getPosition().getX());
    }
}
