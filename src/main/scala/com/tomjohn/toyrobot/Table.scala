package com.tomjohn.toyrobot

case class Table(bottomLeft: Position, topRight: Position) {
  def doCommand(cmd: String): Robot = {
    println(cmd)
    Robot(Position(1, 3), North)
  }
}
