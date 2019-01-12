package de.htwg.se.shithead.view

import de.htwg.se.shithead.controller.Controller

import scala.util.control.Breaks._

object Tui {

  def matches(line: String): Boolean = {
    var line2 = line.toLowerCase
    line2.matches("((\\s)*y(\\s)+[123](\\s)+[123](\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
      "((\\s)*start(\\s)+game(\\s)*)|" +
      "((\\s)*play((\\s)+(\\d+\\s*){1,4}))|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)")
  }

  def eval(line: String) {
    if (matches(line)) {
      line replaceFirst("^ *", "")
      val splitted = line split ("\\s+")
      splitted(0) = splitted(0).toLowerCase
      splitted(0) match {
        case "y" => answerYes(splitted(1) toInt, splitted(2) toInt)
        case "n" => answerNo()
        case "start" => startGame()
        case "add" => println(newUser(splitted(2)))
        case "play" => {
          var list: List[Int] = List()
          for (i <- 1 to splitted.length - 1) list = splitted(i).toInt :: list
          println(playCard(list))
        }
        case "remove" => println(removeUser(splitted(2)))
        case "q" => println("Adios Amigos\n")
      }
    } else println("wrong syntax\n")
  }

  def answerYes(id1: Int, id2: Int) = {
    if (Controller.getState() == 2) {
      println(Controller.changeCards(id1, id2))
      printUser()
    } else "Opeartion not available"
  }

  def answerNo() = {
    if (Controller.getState() == 2) {
      Controller.setNextUser()
      if (Controller.compareToStartUser()) {
        Controller.setState(3)
        println("\nGame starts:\n")
        println("To use a card type in: play ID (up to 4 times)")
        println("You can play any Card since its the Beginning \n")
        printUser()
      } else {
        println("")
        printUser()
      }
    } else "Opeartion not available"
  }

  def newUser(name: String): String = {
    if (Controller.getState == 0) {
      if (Controller.getUserListLength() < 6) {
        if (Controller add (name))
          "New user added: " + name + "\n"
        else
          "There is someone with the same name\n"
      } else {
        "Too many users (delete user with: remove name)\n"
      }
    } else "Opeartion not available\n"
  }

  def startGame() {
    if (Controller.getState() == 0) {
      if (Controller.getUserListLength < 2) {
        println("You need at least 2 players\n")
      } else {
        Controller.setState(1)
        println("Game starts: \n")
        start
        printUser()
      }
    } else "Opeartion not available\n"

  }

  def printUser() = {
    if (Controller.getState() == 2) println(Controller.getCurrentUserName + " Do you want to swap cards? y ID1 ID2 (1-3) / n")
    else if (Controller.getState() == 3) show(true)
    else println("Operation not available")
  }


  def playCard(list: List[Int]): String = {
    if (Controller.getState() == 3) {
      breakable {
        while (true) {
          if (Controller.playCard(list)) {
            println("Played: " + Controller.getPlayedCard(list(0)) +
              "  Amount: " + list.length)
            if (Controller.hasFinished()) {
              println("You placed:" + Controller.getRank() + ".!")
              if (Controller.getRank() == Controller.getUserListLength() - 1) {
                if(Controller.checkIfLastCardWasATen())
                  Controller.setNextUser()
                println("You are last " + Controller.getCurrentUserName() + " :( \n GAME OVER !")
                eval("q")
              }
            }
            Controller.setNextUser()
            printUser()
            break
          } else println("That's not a card")
        }
      }
      "It's your turn!\n"
    } else "Opeartion not available\n"
  }

  def removeUser(name: String): String = {
    var bool: Boolean = false
    if (Controller.getState() == 0) {
      if (Controller.getUserListLength() == 0)
        "No users available\n"
      else if (Controller.remove(name))
        "Success\n"
      else
        "This user does not exist\n"
    } else "Opeartion not available\n"
  }

  def start() = {
    if (Controller.getState() == 1) {
      Controller begin()
      showAll(true)
      Controller.setState(2)
    } else if (Controller.getUserListLength() < 2) {
      println("You need at least 2 players")
    } else println("You are already ingame")
  }

  def show(b: Boolean) = println(Controller.build(b))

  def showAll(b: Boolean) = println(Controller.buildAll(b))
}