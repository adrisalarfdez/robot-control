package org.vw.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CleaningRobot {
    private Position position;
    private char[] instructions;

    public CleaningRobot(Position startPosition, char[] instructions) {
        this.position = startPosition;
        this.instructions = instructions;
    }

    public void executeCommand(char command) {
        switch (command) {
            case 'L' -> position.rotateLeft();
            case 'R' -> position.rotateRight();
            case 'M' -> position.moveForward();
        }
    }

}
