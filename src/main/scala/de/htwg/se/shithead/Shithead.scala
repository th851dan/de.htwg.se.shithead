package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var stack = CardStack()

        var card = stack.pullFromTop

        println("Card pulled from top of Stack: " + card)

        println("\nStack:")
        
        stack.cards.toStream.foreach(x => println(x))

        println("\nAdding Card to top of Stack!")

        stack = stack.addToTop(card)

        stack.cards.toStream.foreach(x => println(x))

        var input: String = ""
        /*do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")*/
    }
}