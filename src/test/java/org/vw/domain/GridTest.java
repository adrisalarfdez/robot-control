package org.vw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridTest {
    private Grid grid;
    private CleaningRobot robot;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5);
        Position startPosition = new Position(grid, 2, 2, Position.Direction.N);
        robot = new CleaningRobot(startPosition, new char[]{});
    }

    @Test
    void testAddRobot() {
        grid.addRobot(robot);
        assertTrue(grid.getRobots().contains(robot));
    }

    @Test
    void testIsInsideGrid_ValidPosition() {
        assertTrue(grid.isCellAvailable(3, 3));
    }

    @Test
    void testIsInsideGrid_InvalidPosition() {
        assertFalse(grid.isCellAvailable(6, 6));
    }

    @Test
    void testIsCellOccupied() {
        grid.addRobot(robot);
        assertFalse(grid.isCellAvailable(2, 2));
    }
}
