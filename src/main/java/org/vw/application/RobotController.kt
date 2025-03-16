package org.vw.application

import org.vw.domain.Grid
import org.vw.infrastructure.GridVisualizer
import org.vw.infrastructure.RobotMovementsFileReader

object RobotController {
    @JvmStatic
    fun main(args: Array<String>) {
        var showGridOnMovements = false
        var filePath = "input.txt"
        for (arg in args) {
            if (arg == "--show-grid") {
                showGridOnMovements = true
            } else {
                filePath = arg
            }
        }

        val grid = readFile(filePath)
        if (grid == null) {
            println("Error: Grid could not be loaded.")
            return
        }
        execMovements(grid, showGridOnMovements)
        consoleOutput(grid)
    }

    private fun readFile(filePath: String): Grid? {
        val fileReader = RobotMovementsFileReader(filePath)
        return fileReader.loadFileContents()
    }

    private fun execMovements(grid: Grid, showGrid: Boolean) {
        for (robot in grid.robots) {
            for (command in robot.commands) {
                robot.executeCommand(command)

                if (showGrid) {
                    GridVisualizer.printGrid(grid)
                }
            }
        }
    }

    private fun consoleOutput(grid: Grid) {
        for (robot in grid.robots) {
            println(robot.position)
        }
    }
}
