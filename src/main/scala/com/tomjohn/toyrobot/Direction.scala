package com.tomjohn.toyrobot

sealed trait Direction {
  def move(p: Position): Position

  def left: Direction

  def right: Direction
}

case object North extends Direction {
  def move(p: Position): Position = {
    Position(p.x, p.y + 1)
  }

  def left: Direction = West

  def right: Direction = East
}

case object South extends Direction {
  def move(p: Position): Position = {
    Position(p.x, p.y - 1)
  }

  def left: Direction = East

  def right: Direction = West
}

case object East extends Direction {
  def move(p: Position): Position = {
    Position(p.x + 1, p.y)
  }

  def left: Direction = North

  def right: Direction = South
}

case object West extends Direction {
  def move(p: Position): Position = {
    Position(p.x - 1, p.y)
  }

  def left: Direction = South

  def right: Direction = North
}

object Direction {
  def lookup(name: String): Direction = {
    name match {
      case "NORTH" => North
      case "SOUTH" => South
      case "EAST" => East
      case "WEST" => West
    }
  }
}
