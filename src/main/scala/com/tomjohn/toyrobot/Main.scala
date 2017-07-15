package com.tomjohn.toyrobot

import scala.io.Source

object Main {

  def doCommand(r: Robot, cmd: String): Robot = {
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

  def main(args: Array[String]): Unit = {
    val r: Robot = Robot(Position(1, 3), North)

    val filename = "robot-test.in"
    val lines = Source.fromFile(filename).getLines
    lines.foreach(l => doCommand(r, l))
  }
}
