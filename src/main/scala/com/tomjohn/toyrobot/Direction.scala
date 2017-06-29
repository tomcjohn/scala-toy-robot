package com.tomjohn.toyrobot

sealed trait Direction {
  def left(): Direction
  def right(): Direction
  def move(p: Position): Position
}

case object North extends Direction {
  def left(): Direction = West

  def right(): Direction = East

  def move(p: Position): Position = Position(p.x, p.y + 1)
}

case object South extends Direction {
  def left(): Direction = East

  def right(): Direction = West

  def move(p: Position): Position = Position(p.x, p.y - 1)
}

case object East extends Direction {
  def left(): Direction = North

  def right(): Direction = South

  def move(p: Position): Position = Position(p.x + 1, p.y)
}

case object West extends Direction {
  def left(): Direction = South

  def right(): Direction = North

  def move(p: Position): Position = Position(p.x - 1, p.y)
}

object DirectionParser {
  def unapply(name: String): Option[Direction] = {
    name.toLowerCase match {
      case "north" => Some(North)
      case "south" => Some(South)
      case "east" => Some(East)
      case "west" => Some(West)
      case _ => None
    }
  }
}
