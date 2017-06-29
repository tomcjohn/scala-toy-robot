package com.tomjohn.toyrobot

case class Position(x: Int, y: Int) {

  override def toString: String = {
    x + "," + y
  }
}
