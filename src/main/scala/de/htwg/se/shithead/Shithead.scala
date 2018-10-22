package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.controller.Controller

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var controller = Controller

        var stack = CardStack
        stack.shuffle()
        var userList = UserList

        // pullFromTop gibt jetzt nur noch eine Karte zurueck

        var card1 = stack.pullFromTop
        var card2 = stack.pullFromTop
        var card3 = stack.pullFromTop

        println(card1)
        println(card2)
        println(card3)

        var input: String = ""
        do {
            input = readLine
            controller.eval(input)
            // Todo: Controller processes input!
        } while(input != "q")
    }
}