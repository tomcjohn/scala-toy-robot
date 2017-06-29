package com.tomjohn.toyrobot

case class Table(bottomLeft: Position, topRight: Position, robots: List[Robot] = List()) {

  val r: Robot = Robot(Position(1, 3), North)

  def doCommand(cmd: String): Robot = {
    // TODO figure out the state monad or something magical here
    //    r = doDoCommand(cmd)
    doDoCommand(cmd)
  }

  def doDoCommand(cmd: String): Robot = {
    val splitCmd: Array[String] = cmd.split(" ")
    splitCmd(0) match {
      case "PLACE" =>
        val placeCmd = splitCmd(1).split(",")
        val newPos = Position(Integer.parseInt(placeCmd(0)), Integer.parseInt(placeCmd(1)))
        val newDir = Direction.lookup(placeCmd(2))
        val newR = Robot(newPos, newDir)
        if (onTable(newR)) newR else r
      case "MOVE" =>
        val newR = r.move
        if (onTable(newR)) newR else r
      case "LEFT" => r.left
      case "RIGHT" => r.right
      case "REPORT" =>
        r.report()
        r
      case _ =>
        println("Unknown command, skipping: " + cmd)
        r
    }
  }

  def onTable(r: Robot): Boolean = {
    r.p.x >= bottomLeft.x && r.p.x <= topRight.x &&
      r.p.y >= bottomLeft.y && r.p.y <= topRight.y
  }
}
