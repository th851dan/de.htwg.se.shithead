package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.controller.Controller
import de.htwg.se.shithead.view.Tui 

import scala.io.StdIn.readLine

object Randomtest {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var controller = Controller

        var stack = CardStack
        stack.shuffle()
        
        var userList = UserList

        // pullFromTop gibt jetzt nur noch eine Karte zurueck
        var input: String = ""
        Tui.eval("add user Hans")
        Tui.eval("add user Peter")
        Tui.eval("start game")
        Tui.eval("y 1 2")
        Tui.eval("n")
        Tui.eval("n")
        println(Controller.buildAll(true))
    }
}