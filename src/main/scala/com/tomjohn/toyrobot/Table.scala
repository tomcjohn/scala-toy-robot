package com.tomjohn.toyrobot

case class Table(bottomLeft: Position, topRight: Position) {
  def doCommand(r: Robot, cmd: String): Robot = {
    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(0).split(",")
        val newX = Integer.parseInt(placeCmd(0))
        val newY = Integer.parseInt(placeCmd(1))
        val newDir = Direction.lookup(placeCmd(2))
        val newRobot = Robot(Position(newX, newY), newDir)
        newRobot
      case "MOVE" =>
        r.move
      case "LEFT" =>
        r.left
      case "RIGHT" =>
        r.right
      case "REPORT" =>
        r.report()
      case _ =>
        println("unrecognised command, skipped: " + splitCmd(0))
        r
    }
  }
}
