package org.vw.domain

class Grid(var maxX: Int, var maxY: Int) {
    var robots: MutableList<CleaningRobot> = ArrayList()

    fun addRobot(robot: CleaningRobot) {
        robots.add(robot)
    }

    fun isCellAvailable(x: Int, y: Int): Boolean {
        return this.isInsideGrid(x, y) && !this.isCellOccupied(x, y)
    }

    private fun isInsideGrid(newX: Int, newY: Int): Boolean {
        return (newX in 0..<maxX) && (newY in 0..<maxY)
    }

    private fun isCellOccupied(x: Int, y: Int): Boolean {
        for (robot in robots) {
            if (robot.position.x == x && robot.position.y == y) {
                return true
            }
        }
        return false
    }
}
