package org.vw.application;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotControllerTest {

    @Test
    void testMain_ExecutesWithoutCrashing() {
        URL file = Objects.requireNonNull(getClass().getClassLoader().getResource("test-input.txt"));
        String[] args = {file.getPath()};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        RobotController.main(args);

        String output = outputStream.toString();
        assertFalse(output.isBlank(), "Execution output should not be empty.");
    }

    @Test
    void testMain_WithShowGridOption() {
        URL file = Objects.requireNonNull(getClass().getClassLoader().getResource("test-input.txt"));
        String[] args = {file.getPath(), "--show-grid"};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        RobotController.main(args);

        String output = outputStream.toString();
        assertTrue(output.contains("["), "Execution output should show the grid.");
    }

    @Test
    void testMain_InvalidFile_DoesNotCrash() {
        String[] args = {"nonexistent-file.txt"};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        RobotController.main(args);

        String output = outputStream.toString();
        assertTrue(output.contains("Error"), "Uncontrolled errors when non-existent is file provided.");
    }
}
