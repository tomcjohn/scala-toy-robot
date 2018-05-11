package com.tomjohn.toyrobot

case class Table(bottomLeft: Position, topRight: Position) {
  def doCommand(cmd: String, r: Robot): Robot = {
    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newX = placeCmd(0).toInt
        val newY = placeCmd(1).toInt
        val newD = Direction.lookup(placeCmd(2))
        Robot(Position(newX, newY), newD)
      case "MOVE" => r.move()
      case "LEFT" => r.left()
      case "RIGHT" => r.right()
      case "REPORT" => r.report()
      case _ =>
        println("Unrecognised command: " ++ cmd)
        r
    }
  }
}
