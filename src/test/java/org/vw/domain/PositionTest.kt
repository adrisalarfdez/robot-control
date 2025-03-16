package org.vw.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PositionTest {
    private var position: Position? = null

    @BeforeEach
    fun setUp() {
        position = Position(Grid(5, 5), 2, 2, Position.Direction.N)
    }

    @Test
    fun testMoveForward_ValidMove() {
        position!!.moveForward()
        Assertions.assertEquals(3, position!!.y)
    }

    @Test
    fun testMoveForward_InvalidMove() {
        position!!.y = 5
        position!!.moveForward()
        Assertions.assertEquals(5, position!!.y)
    }

    @Test
    fun testRotateLeft() {
        position!!.rotateLeft()
        Assertions.assertEquals(Position.Direction.W, position!!.direction)
    }

    @Test
    fun testRotateRight() {
        position!!.rotateRight()
        Assertions.assertEquals(Position.Direction.E, position!!.direction)
    }
}
