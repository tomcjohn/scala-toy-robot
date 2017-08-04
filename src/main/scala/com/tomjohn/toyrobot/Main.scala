package com.tomjohn.toyrobot

import scala.io.Source
import scalaz.State

object Main {

  def main(args: Array[String]): Unit = {
    val table: Table = Table(Position(0, 0), Position(5, 5))

    val filename = "robot-test.in"
    val lines = Source.fromFile(filename).getLines

    val finalState: State[Option[Robot], Unit] = lines.map(table.doCommand).fold(State.state())(
      (x, y) => {
        x.flatMap(_ => y)
      }
    )
    finalState.apply(Some(Robot(Position(1,1), North)))
  }
}
