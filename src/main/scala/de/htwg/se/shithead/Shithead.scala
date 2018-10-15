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

        val tuple = stack.pullFromTop
        stack = tuple._2
        var card = tuple._1
        println("\nCard pulled from top of Stack: " + card)
        stack.cardStack.toStream.foreach(x => println(x))

        stack = stack.addToTop(card)
        println("\nCard added to top of Stack: " + card)    
        stack.cardStack.toStream.foreach(x => println(x))

// !new User
        var userList: List[User] = List()
        userList = new User("Marius") :: userList
        userList = new User("Julius") :: userList
        println("\nUserList: " + userList)
        println("UUID: " + userList(0).UUID + " Name: " + userList(0).NAME)
        println("UUID: " + userList(1).UUID + " Name: " + userList(1).NAME)

        /*var input: String = ""
        do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")*/
    }
}