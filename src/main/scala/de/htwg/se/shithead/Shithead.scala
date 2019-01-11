package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.view.Tui

import scala.io.StdIn.readLine

object Shithead {

  def main(args: Array[String]) {
    println("This is Shithead!")

    var stack = CardStack
    stack.shuffle()
    // pullFromTop gibt jetzt nur noch eine Karte zurueck
    var input: String = ""
    do {
      input = readLine
      // Controller processes input!
      Tui.eval(input)
    } while (input != "q" && input != "Q")
  }
}