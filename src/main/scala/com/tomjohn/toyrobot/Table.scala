package com.tomjohn.toyrobot

case class Table(bottomLeft: Position, topRight: Position) {
  def doCommand(r: Robot, cmd: String): Robot = {
    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newX = Integer.parseInt(placeCmd(0))
        val newY = Integer.parseInt(placeCmd(1))
        val newDir = Direction.lookup(placeCmd(2))
        Robot(Position(newX, newY), newDir)
      case "MOVE" =>
        r.move
      case "LEFT" =>
        r.left
      case "RIGHT" =>
        r.right
      case _ =>
        println("skip unknown command")
        r
    }
  }
}
