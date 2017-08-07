package com.tomjohn.toyrobot

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {

    val filename = "robot-test.in"
    val lines = Source.fromFile(filename).getLines

    lines.foreach(l => println(l))
  }
}
