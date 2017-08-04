package com.tomjohn.toyrobot

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    var r: Robot = Robot(Position(1, 1), North)

    val filename = "robot-test.in"
    val lines: Iterator[String] = Source.fromFile(filename).getLines

    lines.foreach(line => println(line))
  }
}
