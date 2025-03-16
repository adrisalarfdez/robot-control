package org.vw.application

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.vw.application.RobotController.main
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

internal class RobotControllerTest {
    @Test
    fun testMain_ExecutesWithoutCrashing() {
        val file = Objects.requireNonNull(javaClass.classLoader.getResource("test-input.txt"))
        val args = arrayOf(file.path)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(args)

        val output = outputStream.toString()
        Assertions.assertFalse(output.isBlank(), "Execution output should not be empty.")
    }

    @Test
    fun testMain_WithShowGridOption() {
        val file = Objects.requireNonNull(javaClass.classLoader.getResource("test-input.txt"))
        val args = arrayOf(file.path, "--show-grid")

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(args)

        val output = outputStream.toString()
        Assertions.assertTrue(output.contains("["), "Execution output should show the grid.")
    }

    @Test
    fun testMain_InvalidFile_DoesNotCrash() {
        val args = arrayOf("nonexistent-file.txt")

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(args)

        val output = outputStream.toString()
        Assertions.assertTrue(output.contains("Error"), "Uncontrolled errors when non-existent is file provided.")
    }
}
