package org.vw.infrastructure

import org.vw.domain.CleaningRobot
import org.vw.domain.Grid
import org.vw.domain.Position
import org.vw.domain.Position.Direction
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class RobotMovementsFileReader(private val filePath: String) {
    fun loadFileContents(): Grid? {
        var scanner: Scanner? = null
        var grid: Grid? = null

        try {
            val inputFile = File(filePath)

            println("Reading input file: $filePath")
            scanner = Scanner(inputFile)

            val gridX = scanner.nextInt()
            val gridY = scanner.nextInt()
            grid = Grid(gridX + 1, gridY + 1)
            scanner.nextLine()

            while (scanner.hasNextLine()) {
                val x = scanner.nextInt()
                val y = scanner.nextInt()
                val direction = Direction.valueOf(scanner.next())
                scanner.nextLine()

                if (!scanner.hasNextLine()) break
                val commands = scanner.nextLine()

                val robot = CleaningRobot(Position(grid, x, y, direction), commands.toCharArray().toList())
                grid.addRobot(robot)
            }
        } catch (e: FileNotFoundException) {
            println("Error: File not found")
        } finally {
            scanner?.close()
        }
        return grid
    }
}
