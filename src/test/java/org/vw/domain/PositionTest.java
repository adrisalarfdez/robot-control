package org.vw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(new Grid(5, 5), 2, 2, Position.Direction.N);
    }

    @Test
    void testMoveForward_ValidMove() {
        position.moveForward();
        assertEquals(3, position.getY());
    }

    @Test
    void testMoveForward_InvalidMove() {
        position.setY(5);
        position.moveForward();
        assertEquals(5, position.getY());
    }

    @Test
    void testRotateLeft() {
        position.rotateLeft();
        assertEquals(Position.Direction.W, position.getDirection());
    }

    @Test
    void testRotateRight() {
        position.rotateRight();
        assertEquals(Position.Direction.E, position.getDirection());
    }
}
