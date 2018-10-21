package de.htwg.se.shithead

import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.User

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var stack = CardStack()
        var userList: List[User] = List()

        var input: String = ""
        do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")
    }
}