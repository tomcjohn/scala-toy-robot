package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position, robots: List[Robot] = List()) {


  def doCommand(cmd: String): Robot = {
    val r: Robot = Robot(Position(1, 3), North)

    val splitCmd: Array[String] = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newPos = Position(Integer.parseInt(placeCmd(0)), Integer.parseInt(placeCmd(1)))
        val newDir = Direction.lookup(placeCmd(2))
        val newR = Robot(newPos, newDir)
        if (onTable(newR)) newR else r
      case "MOVE" =>
        val newR = r.move
        if (onTable(newR)) newR else r
      case "LEFT" => r.left
      case "RIGHT" => r.right
      case "REPORT" =>
        r.report()
        r
      case _ =>
        println("Unknown command, skipping: " + cmd)
        r
    }
  }

  def doDoCommand(cmd: String): State[Robot, Unit] = {
    val splitCmd: Array[String] = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        State(s => {
          val newPos = Position(Integer.parseInt(placeCmd(0)), Integer.parseInt(placeCmd(1)))
          val newDir = Direction.lookup(placeCmd(2))
          val newR = Robot(newPos, newDir)
          if (onTable(newR)) (newR, ()) else (s, ())
        })
      case "MOVE" =>
        State(s => {
          val newR = s.move
          if (onTable(newR)) (newR, ()) else (s, ())
        })
      case "LEFT" => State(s => (s.left, ()))
      case "RIGHT" => State(s => (s.right, ()))
      case "REPORT" => State(s => (s.report(), ()))
      //      case _ =>
      //        println("Unknown command, skipping: " + cmd)
      //        State(s => (_, ()))
    }
  }

  def onTable(r: Robot): Boolean = {
    r.p.x >= bottomLeft.x && r.p.x <= topRight.x &&
      r.p.y >= bottomLeft.y && r.p.y <= topRight.y
  }
}
