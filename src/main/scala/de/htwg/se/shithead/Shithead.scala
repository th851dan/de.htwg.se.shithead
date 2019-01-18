package de.htwg.se.shithead

import de.htwg.se.shithead.controller.CellChanged
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.model._
import de.htwg.se.shithead.model.baseImp._
import de.htwg.se.shithead.view.Gui.Gui
import de.htwg.se.shithead.view.Tui

import scala.io.StdIn.readLine

object Shithead {

  def main(args: Array[String]) {
    println("This is Shithead!")
    val stack:CardStack = new CardStack(new Stack(getCards(),true).shuffle(),new Stack(List(), false),false, true)
    val userList:UserList = new UserList(List(), new User("s",List(),List())) // falls irgendwas mit currentuser = s auftaucht hier ändern
    val con:Controller = new Controller(userList,stack)
    val tui:Tui = new Tui(con)
    val gui:Gui = new Gui(con)


    var input: String = ""
    do {
      input = readLine
      // Controller processes input!
      tui.eval(input)
    } while (input != "q" && input != "Q")
  }

  def getCards(): List[Card] = {
    val suites = Set(Spade, Heart, Club, Diamond)
    val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
    for (r <- ranks; s <- suites) yield Card(r, s, false)
  }
}