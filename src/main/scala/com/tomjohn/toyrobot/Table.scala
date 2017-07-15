package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position) {

  val flow: State[Robot, Unit] = ???

  def doCommand(r: Robot, cmd: String): Robot = {
    val value: State[Robot, Unit] = State.get()

    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val splitPlaceCmd = splitCmd(1).split(",")
        val newX = Integer.parseInt(splitPlaceCmd(0))
        val newY = Integer.parseInt(splitPlaceCmd(1))
        val newDir = Direction.lookup(splitPlaceCmd(2))
        Robot(Position(newX, newY), newDir)
      case "LEFT" =>
        Robot(r.p, r.d.left())
      case "RIGHT" =>
        Robot(r.p, r.d.right())
      case "MOVE" =>
        Robot(r.d.move(r.p), r.d)
      case "REPORT" =>
        r.report()
      case _ =>
        println("Unrecognised command, skipped: " + splitCmd(0))
        r
    }
  }

  def onTable(r: Robot): Boolean = {
    r.p.x < bottomLeft.x || r.p.x > topRight.x ||
      r.p.y < bottomLeft.y || r.p.y > topRight.y
  }
}
