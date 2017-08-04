package com.tomjohn.toyrobot

import scalaz.State

case class Table(bottomLeft: Position, topRight: Position) {

  def doCommand(cmd: String): State[Option[Robot], Unit] = {
    val splitCmd = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newX = Integer.parseInt(placeCmd(0))
        val newY = Integer.parseInt(placeCmd(1))
        val newDir = Direction.lookup(placeCmd(2))
        val newRobot = Robot(Position(newX, newY), newDir)
        adjustRobot(_ => newRobot, onTable)
      case "MOVE" =>
        adjustRobot(_.move)
      case "LEFT" =>
        adjustRobot(_.left)
      case "RIGHT" =>
        adjustRobot(_.right)
      case "REPORT" =>
        adjustRobot(_.report())
      case _ =>
        println("unrecognised command, skipped: " + splitCmd(0))
        State.state(())
    }
  }

  private def adjustRobot(action: Robot => Robot, accept: Option[Robot] => Boolean = always): State[Option[Robot], Unit] = {
    for {
      maybeRobot: Option[Robot] <- State.gets((o: Option[Robot]) => o.map(action))
      _ <- if (accept(maybeRobot)) State.put(maybeRobot) else State.state(())
    } yield ()
  }

  private def onTable(o: Option[Robot]): Boolean = {
    o match {
      case Some(r) =>
        onTable(r)
      case None =>
        false
    }
  }

  private def onTable(r: Robot): Boolean = {
    r.p.x >= bottomLeft.x && r.p.x <= topRight.x &&
      r.p.y >= bottomLeft.y && r.p.y <= topRight.y
  }

  def always(o: Option[Robot]): Boolean = {
    true
  }
}
