package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var stack = CardStack()

        stack.cardStack.toStream.foreach(x => println(x))

        val tuple = stack.pullFromTop

        stack = tuple._2
        var card = tuple._1

        println("\nCard pulled from top of Stack: " + card)

        stack.cardStack.toStream.foreach(x => println(x))

        stack = stack.addToTop(card)

        println("\nCard added to top of Stack: " + card)
        
        stack.cardStack.toStream.foreach(x => println(x))


        var input: String = ""
        /*do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")*/
    }
}