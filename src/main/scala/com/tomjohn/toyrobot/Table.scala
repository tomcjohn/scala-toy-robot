package com.tomjohn.toyrobot

import scala.util.Try
import scala.util.matching.Regex
import scalaz.State

case class Table(bottomLeft: Position, topRight: Position, robots: List[Robot] = List()) {

  private val placePattern: Regex = "(\\d+),(\\d+),(.+)".r

  private object IntParser {
    def unapply(s: String): Option[Int] = {
      Try(Integer.parseInt(s)).toOption
    }
  }

  def doCommand(cmd: String): State[Option[Robot], Unit] = {
    val splitCmd: Array[String] = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        splitCmd(1) match {
          case placePattern(IntParser(x), IntParser(y), DirectionParser(d)) =>
            val newR = Robot(Position(x, y), d)
            if (onTable(newR)) State.put(Option(newR)) else State.state(())
          case _ => State.state(())
        }
      case "MOVE" =>
        adjustRobot(_.move, onTable)
      case "LEFT" =>
        adjustRobot(_.left)
      case "RIGHT" =>
        adjustRobot(_.right)
      case "REPORT" =>
        adjustRobot(_.report())
      case _ =>
        State.state(())
    }
  }

  private def adjustRobot(action: Robot => Robot, accept: Option[Robot] => Boolean = always): State[Option[Robot], Unit] = {
    for {
      maybeRobot <- State.gets((y: Option[Robot]) => y.map(action))
      _ <- if (accept(maybeRobot)) State.put(maybeRobot) else State.state[Option[Robot], Unit](())
    } yield ()
  }

  private def always(r: Option[Robot]): Boolean = {
    true
  }

  private def onTable(o: Option[Robot]): Boolean = {
    o match {
      case None => false
      case Some(r) => onTable(r)
    }
  }

  private def onTable(r: Robot): Boolean = {
    r.p.x >= bottomLeft.x && r.p.x <= topRight.x &&
      r.p.y >= bottomLeft.y && r.p.y <= topRight.y
  }
}
