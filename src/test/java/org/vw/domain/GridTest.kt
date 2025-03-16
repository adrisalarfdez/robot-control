package org.vw.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GridTest {
    private var grid: Grid? = null
    private var robot: CleaningRobot? = null

    @BeforeEach
    fun setUp() {
        grid = Grid(5, 5)
        val startPosition = Position(grid!!, 2, 2, Position.Direction.N)
        robot = CleaningRobot(startPosition, listOf())
    }

    @Test
    fun testAddRobot() {
        grid!!.addRobot(robot!!)
        Assertions.assertTrue(grid!!.robots.contains(robot))
    }

    @Test
    fun testIsInsideGrid_ValidPosition() {
        Assertions.assertTrue(grid!!.isCellAvailable(3, 3))
    }

    @Test
    fun testIsInsideGrid_InvalidPosition() {
        Assertions.assertFalse(grid!!.isCellAvailable(6, 6))
    }

    @Test
    fun testIsCellOccupied() {
        grid!!.addRobot(robot!!)
        Assertions.assertFalse(grid!!.isCellAvailable(2, 2))
    }
}
