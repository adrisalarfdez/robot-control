package org.vw.domain

data class CleaningRobot(
    var position: Position,
    val commands: List<Char>
) {
    fun executeCommand(command: Char) {
        when (command) {
            'L' -> position.rotateLeft()
            'R' -> position.rotateRight()
            'M' -> position.moveForward()
        }
    }
}