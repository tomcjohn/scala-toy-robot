package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position) {

  def doCommand(cmd: String): State[Option[Robot], Unit] = {

    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val splitPlaceCmd = splitCmd(1).split(",")
        val newX = Integer.parseInt(splitPlaceCmd(0))
        val newY = Integer.parseInt(splitPlaceCmd(1))
        val newDir = Direction.lookup(splitPlaceCmd(2))
        val newRobot = Robot(Position(newX, newY), newDir)
        adjustRobot(_ => newRobot, onTable)
      case "LEFT" =>
        adjustRobot(_.left)
      case "RIGHT" =>
        adjustRobot(_.right)
      case "MOVE" =>
        adjustRobot(_.move, onTable)
      case "REPORT" =>
        adjustRobot(_.report())
      case _ =>
        println("Unrecognised command, skipped: " + splitCmd(0))
        State.state(())
    }
  }

  private def adjustRobot(action: Robot => Robot, accept: Option[Robot] => Boolean = always): State[Option[Robot], Unit] = {
    for {
      maybeRobot <- State.gets((o: Option[Robot]) => o.map(action))
      _ <- if (accept(maybeRobot)) State.put(maybeRobot) else State.state[Option[Robot], Unit](())
    } yield ()
  }

  private def always(r: Option[Robot]): Boolean = {
    true
  }

  def onTable(o: Option[Robot]): Boolean = {
    o match {
      case Some(r) =>
        onTable(r)
      case _ =>
        false
    }
  }

  def onTable(r: Robot): Boolean = {
    r.p.x < bottomLeft.x || r.p.x > topRight.x ||
      r.p.y < bottomLeft.y || r.p.y > topRight.y
  }
}
