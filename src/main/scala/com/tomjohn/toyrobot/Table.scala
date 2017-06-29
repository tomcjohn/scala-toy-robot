package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position, robots: List[Robot] = List()) {

  def doCommand(cmd: String): State[Option[Robot], Unit] = {
    val splitCmd: Array[String] = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        State(s => {
          val newPos = Position(Integer.parseInt(placeCmd(0)), Integer.parseInt(placeCmd(1)))
          val newDir = Direction.lookup(placeCmd(2))
          val newR = Robot(newPos, newDir)
          if (onTable(newR)) (Option(newR), ()) else (s, ())
        })
      case "MOVE" =>
        State(s => {
          val maybeRobot = s.map(r => r.move)
          if (maybeRobot.isDefined && onTable(maybeRobot.get)) (Option(maybeRobot.get), ()) else (s, ())
        })
      case "LEFT" =>
        State(s => {
          val maybeRobot = s.map(r => r.left)
          if (maybeRobot.isDefined) (Option(maybeRobot.get), ()) else (s, ())
        })
      case "RIGHT" =>
        State(s => {
          val maybeRobot = s.map(r => r.right)
          if (maybeRobot.isDefined) (Option(maybeRobot.get), ()) else (s, ())
        })
      case "REPORT" =>
        State(s => {
          val maybeRobot = s.map(r => r.report())
          if (maybeRobot.isDefined) (Option(maybeRobot.get), ()) else (s, ())
        })
      case _ =>
        println("Unknown command, skipping: " + cmd)
        State(s => (Option.empty, ()))
    }
  }

  def onTable(r: Robot): Boolean = {
    r.p.x >= bottomLeft.x && r.p.x <= topRight.x &&
      r.p.y >= bottomLeft.y && r.p.y <= topRight.y
  }
}
