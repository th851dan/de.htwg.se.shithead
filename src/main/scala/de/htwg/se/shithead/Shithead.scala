package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.User

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

// !new CardStack and basic overview of functions
        var stack = CardStack()
        stack.cardStack.toStream.foreach(x => println(x))

        /*var input: String = ""
        do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")*/
    }
}