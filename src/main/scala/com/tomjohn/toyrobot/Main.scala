package com.tomjohn.toyrobot

object Main {
  def main(args: Array[String]): Unit = {
    val r: Robot = Robot(Position(1, 3), North)
    r.move()
      .report()
      .move()
      .report()
  }
}
