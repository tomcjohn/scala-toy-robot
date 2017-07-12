package com.tomjohn.toyrobot

case class Robot(p: Position, d: Direction) {

  def left(): Robot = {
    Robot(p, d.left())
  }

  def right(): Robot = {
    Robot(p, d.right())
  }

  def move(): Robot = {
    Robot(d.move(p), d)
  }

  def report(): Robot = {
    println(p + "," + d)
    this
  }
}
