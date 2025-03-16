package org.vw.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private final Grid grid;
    private int x;
    private int y;
    private Direction direction;

    public enum Direction {
        N, E, S, W
    }

    public Position(Grid grid, int x, int y, Direction direction) {
        this.grid = grid;
        if (!grid.isCellAvailable(x, y)) {
            throw new IndexOutOfBoundsException("Position "+x+" "+y+" out of grill bounds");
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void moveForward() {
        int newX = x, newY = y;

        switch (direction) {
            case N -> newY += 1;
            case S -> newY -= 1;
            case E -> newX += 1;
            case W -> newX -= 1;
        }

        if (grid.isCellAvailable(newX, newY)) {
            this.x = newX;
            this.y = newY;
        }
    }

    public void rotateLeft() {
        direction = switch (direction) {
            case N -> Direction.W;
            case W -> Direction.S;
            case S -> Direction.E;
            case E -> Direction.N;
        };
    }

    public void rotateRight() {
        direction = switch (direction) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
    }

    public String toString() {
        return x + " " + y + " " + direction;
    }
}