package org.vw.domain

class Position(private val grid: Grid, var x: Int, var y: Int, var direction: Direction) {
    enum class Direction {
        N, E, S, W
    }

    init {
        if (!grid.isCellAvailable(x, y)) {
            throw IndexOutOfBoundsException("Position $x $y out of grill bounds")
        }
    }

    fun moveForward() {
        var newX = x
        var newY = y

        when (direction) {
            Direction.N -> newY += 1
            Direction.S -> newY -= 1
            Direction.E -> newX += 1
            Direction.W -> newX -= 1
        }

        if (grid.isCellAvailable(newX, newY)) {
            this.x = newX
            this.y = newY
        }
    }

    fun rotateLeft() {
        direction = when (direction) {
            Direction.N -> Direction.W
            Direction.W -> Direction.S
            Direction.S -> Direction.E
            Direction.E -> Direction.N
        }
    }

    fun rotateRight() {
        direction = when (direction) {
            Direction.N -> Direction.E
            Direction.E -> Direction.S
            Direction.S -> Direction.W
            Direction.W -> Direction.N
        }
    }

    override fun toString(): String {
        return "$x $y $direction"
    }
}