package de.htwg.se.shithead

import de.htwg.se.shithead.model.{CardStack, UserList}
import de.htwg.se.shithead.view.Tui

object Randomtest {

  def main(args: Array[String]) {
    Tui.eval("add user hans")
    Tui.eval("add user peter")
    Tui.eval("start game")
    Tui.eval("y 1 1")
    Tui.eval("n")
    Tui.eval("n")
    Tui.eval("play 1")
    Tui.eval("play 2")
    Tui.eval("play 2")
    Tui.eval("play 3")
    Tui.eval("play 3")
    Tui.eval("play 2")
    Tui.eval("play 1")
    Tui.eval("play 3")
    Tui.eval("play 2")
  }
}