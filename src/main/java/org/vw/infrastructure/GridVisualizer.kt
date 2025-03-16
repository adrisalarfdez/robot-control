package org.vw.infrastructure

import org.vw.domain.Grid

object GridVisualizer {
    fun printGrid(grid: Grid) {
        print("\u001b[H\u001b[2J")
        System.out.flush()

        for (y in grid.maxY downTo 0) {
            print(" " + (if (y < grid.maxY) y else "-") + " ")
            for (x in 0..<grid.maxX) {
                if (y == grid.maxY) {
                    print(" $x  ")
                    continue
                }
                var cellContent = "  "
                for (robotIndex in grid.robots.indices) {
                    val robot = grid.robots[robotIndex]
                    if (robot.position.x == x && robot.position.y == y) {
                        cellContent = (robotIndex + 1).toString() + "" + robot.position.direction
                        break
                    }
                }
                print("[$cellContent]")
            }
            println()
        }
        println()

        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}
