package com.tomjohn.toyrobot

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val t = Table(Position(0,0), Position(9,9))
    val r = Robot(Position(3,3), North)
    val cmds: Seq[String] = Seq("PLACE 1,3,NORTH", "REPORT", "RIGHT", "MOVE", "SDSD", "LEFT", "MOVE", "REPORT")
    cmds.map(c => t.doCommand(c, r))
    println("Goodbye, world!")
  }
}
