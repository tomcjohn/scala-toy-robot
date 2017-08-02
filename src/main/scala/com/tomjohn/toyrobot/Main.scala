package com.tomjohn.toyrobot

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val table: Table = Table(Position(0, 0), Position(5, 5))

    val filename = "robot-test.in"
    val lines = Source.fromFile(filename).getLines

    lines.foreach(l => table.doCommand(l))
  }
}
