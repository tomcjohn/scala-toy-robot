package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position) {
  def doCommand(r: Robot, cmd: String): State[Option[Robot], Unit] = {
    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newX = Integer.parseInt(placeCmd(0))
        val newY = Integer.parseInt(placeCmd(1))
        val newDir = Direction.lookup(placeCmd(2))
        State.put(Some(Robot(Position(newX, newY), newDir)))
      case "MOVE" =>
        State.put(Some(r.move))
      case "LEFT" =>
        State.put(Some(r.left))
      case "RIGHT" =>
        State.put(Some(r.right))
      case "REPORT" =>
        r.report()
        State.state(())
      case _ =>
        println("skip unknown command")
        State.state(())
    }
  }
}
