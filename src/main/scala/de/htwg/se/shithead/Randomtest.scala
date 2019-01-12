package de.htwg.se.shithead

import de.htwg.se.shithead.view.Tui

object Randomtest {

  def main(args: Array[String]) {
    Tui.eval("add user hans")
    Tui.eval("add user peter")
    Tui.eval("start game")
    Tui.eval("n")
    Tui.eval("n")
    Tui.eval("play 2")
  }
}