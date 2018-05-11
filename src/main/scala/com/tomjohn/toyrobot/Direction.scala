package com.tomjohn.toyrobot

sealed trait Direction {
  def move(p: Position): Position
  def left: Direction
  def right: Direction
}

case object North extends Direction {
  def move(p: Position) = Position(p.x, p.y + 1)
  def left: Direction = West
  def right: Direction = East
}

case object East extends Direction {
  def move(p: Position) = Position(p.x, p.y + 1)
  def left: Direction = West
  def right: Direction = East
}

case object South extends Direction {
  def move(p: Position) = Position(p.x, p.y + 1)
  def left: Direction = West
  def right: Direction = East
}

case object West extends Direction {
  def move(p: Position) = Position(p.x, p.y + 1)
  def left: Direction = West
  def right: Direction = East
}

object Direction {
  def lookup(s: String): Direction = {
    s match {
      case "NORTH" => North
      case "EAST"  => East
      case "SOUTH" => South
      case "WEST"  => West
    }
  }
}
