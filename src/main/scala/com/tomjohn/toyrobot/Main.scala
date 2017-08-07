package com.tomjohn.toyrobot

import scala.io.Source
import scalaz.State

object Main {

  def main(args: Array[String]): Unit = {
    val t: Table = Table(Position(0, 0), Position(5, 5))

    val filename = "robot-test.in"

    val lines: Iterator[String] = Source.fromFile(filename).getLines

    val finalState: State[Option[Robot], Unit] = lines.map(l => t.doCommand(l)).fold(State.state(()))(
      (x, y) => {
        x.flatMap(_ => y)
      }
    )
    finalState.apply(Option.empty)
  }
}
