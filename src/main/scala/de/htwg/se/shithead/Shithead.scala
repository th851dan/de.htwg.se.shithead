package de.htwg.se.shithead

import model.CardStack

import scala.io.StdIn.readLine

object Shithead {

    def main(args: Array[String]) {
        println("This is Shithead!")

        var input: String = ""
        do {
            input = readLine
            // Todo: Controller processes input!
        } while(input != "q")
    }
}