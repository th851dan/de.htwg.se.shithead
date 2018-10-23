package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.controller.Controller
import de.htwg.se.shithead.view.Tui

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var controller = Controller

        var stack = CardStack
        stack.shuffle()
        
        var userList = UserList

        // pullFromTop gibt jetzt nur noch eine Karte zurueck
        var input: String = ""
        do {
            input = readLine
            // Controller processes input!
            Tui.eval(input)
        } while(input != "q")
    }
}