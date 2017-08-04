package com.tomjohn.toyrobot

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val t: Table = Table(Position(0, 0), Position(5,5))

    val filename = "robot-test.in"
    val lines: Iterator[String] = Source.fromFile(filename).getLines

    var r: Robot = Robot(Position(1, 1), North)
    lines.map(l => t.doCommand(r, l))
  }
}
