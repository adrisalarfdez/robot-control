package org.vw.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CleaningRobotTest {
    private var grid: Grid? = null
    private var robot: CleaningRobot? = null

    @BeforeEach
    fun setUp() {
        grid = Grid(5, 5)
        val startPosition = Position(grid!!, 2, 2, Position.Direction.N)
        robot = CleaningRobot(startPosition, listOf('M', 'L', 'M', 'R', 'M'))
    }

    @Test
    fun testExecuteCommand_MoveForward() {
        robot!!.executeCommand('M')
        Assertions.assertEquals(2, robot!!.position.x)
        Assertions.assertEquals(3, robot!!.position.y)
        Assertions.assertEquals(Position.Direction.N, robot!!.position.direction)
    }

    @Test
    fun testExecuteCommand_RotateLeft() {
        robot!!.executeCommand('L')
        Assertions.assertEquals(Position.Direction.W, robot!!.position.direction)
    }

    @Test
    fun testExecuteCommand_RotateRight() {
        robot!!.executeCommand('R')
        Assertions.assertEquals(Position.Direction.E, robot!!.position.direction)
    }

    @Test
    fun testExecuteCommand_EastBlocked() {
        robot!!.position.x = grid!!.maxX
        robot!!.position.direction = Position.Direction.E
        robot!!.executeCommand('M')
        Assertions.assertEquals(grid!!.maxX, robot!!.position.x)
    }

    @Test
    fun testExecuteCommand_NorthBlocked() {
        robot!!.position.y = grid!!.maxY
        robot!!.position.direction = Position.Direction.N
        robot!!.executeCommand('M')
        Assertions.assertEquals(grid!!.maxY, robot!!.position.y)
    }

    @Test
    fun testExecuteCommand_SouthBlocked() {
        robot!!.position.y = 0
        robot!!.position.direction = Position.Direction.S
        robot!!.executeCommand('M')
        Assertions.assertEquals(0, robot!!.position.y)
    }

    @Test
    fun testExecuteCommand_WestBlocked() {
        robot!!.position.x = 0
        robot!!.position.direction = Position.Direction.W
        robot!!.executeCommand('M')
        Assertions.assertEquals(0, robot!!.position.x)
    }
}
