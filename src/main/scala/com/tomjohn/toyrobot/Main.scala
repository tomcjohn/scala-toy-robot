package com.tomjohn.toyrobot

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val r: Robot = Robot(Position(1, 3), North)

    val filename = "robot-test.in"
    val lines = Source.fromFile(filename).getLines
    lines.foreach(l => doCommand(r, l))
  }
}
