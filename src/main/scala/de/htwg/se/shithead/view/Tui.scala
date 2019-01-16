package de.htwg.se.shithead.view

import de.htwg.se.shithead.controller.Controller

import scala.util.control.Breaks._

object Tui {

  def finished() = {
    println("You placed:" + Controller.getRank() + ".!")
    if (Controller.getRank() == Controller.getUserListLength() - 1) {
      println("You are last " + Controller.getCurrentUserName() + " :( \n GAME OVER !")
      eval("q")
    }
  }

  def matches(line: String): Boolean = line.toLowerCase.matches("((\\s)*y(\\s)+[123](\\s)+[123](\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
      "((\\s)*start(\\s)+game(\\s)*)|" +
      "((\\s)*play((\\s)+(\\d+\\s*){1,4}))|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)")

  def eval(line: String) {
    if (matches(line)) {
      line replaceFirst("^ *", "")
      val splitted = line.split("\\s+")
      splitted(0) = splitted(0).toLowerCase
      splitted(0) match {
        case "y" => answerYes(splitted(1) toInt, (splitted(2) toInt))
        case "n" => answerNo()
        case "start" => startGame()
        case "add" => println(newUser(splitted(2)))
        case "play" => {
          var list: List[Int] = List()
          for (i <- 1 to splitted.length - 1) list = splitted(i).toInt - 1 :: list
          println(playCard(list))
        }
        case "remove" => println(removeUser(splitted(2)))
        case "q" => println("Adios Amigos\n")
      }
    } else println("wrong syntax\n")
  }
  //TODO: Karte wird nicht vom Tablestack gelöscht
  def answerYes(id1: Int, id2: Int): Unit = if (Controller.getState() == 2) {
    println(Controller.changeCards(id1, id2))
    show(true)
    printUser()
  }

  def answerNo(): Unit = Controller.getState() match {
    case 2 => {
      Controller.setNextUser()
      if (Controller.compareToStartUser()) {
        Controller.setState(3)
        println("\nGame starts:\nTo use a card type in: play ID (up to 4 times)\nYou can play any Card since its the Beginning \n")
        printUser()
      } else printUser()
    }
    case _ => "Operation not available\n"
  }

  def newUser(name: String): String = Controller.getState() match {
    case 0 => {
      if (Controller.getUserListLength() < 5) {
        if (Controller.add(name))
          "New user added: " + name + "\n"
        else
          "There is someone with the same name\n"
      } else {
        "Too many users (delete user with: remove name)\n"
      }
    }
    case _ =>
      "Operation not available\n"
  }

  def startGame(): Unit = Controller.getState() match {
    case 0 => {
      if (Controller.getUserListLength < 2) {
        println("You need at least 2 players\n")
      } else {
        Controller.setState(1)
        println("Game starts: \n")
        start()
        printUser()
      }
    }
    case _ => "Operation not available\n"
  }

  def printUser(): Unit = Controller.getState() match {
      case 2 => println(Controller.getCurrentUserName + " Do you want to swap cards? y ID1 ID2 (1-3) / n")
      case 3 => Controller.currentUserHasHand() match {
        case true => show(false)
        case false => show(true)
      }
      case _ => println("Operation not available")
  }

  def playCard(list: List[Int]): String = {
    Controller.getState() match {
      case 3 => {
        if(Controller.checkList(list)) {
          if (Controller.playCard(list)) {
            println("Played: " + Controller.getPlayedCard() +
              "  Amount: " + list.length + "\n")
            printUser()
          } else {
            println("Unlucky you! You have to pickup the cards")
            printUser()
          }
          "It's your turn!\n"
        } else
          "No legit number :( Try again! \n"
      }
      case _ =>"Operation not available\n"
      }
  }

  def removeUser(name: String): String = {
    Controller.getState() match {
      case 0 => {
        if (Controller.getUserListLength() == 0) "No users available\n"
        else if (Controller.remove(name)) "Success\n"
        else "User does not exsist"
      }
      case _ => "Operation not available\n"
    }
  }

  def start(): Unit = {
    if (Controller.getState() == 1) {
      Controller.begin()
      showAll(true)
      Controller.setState(2)
    } else if (Controller.getUserListLength() < 2) {
      println("You need at least 2 players")
    } else println("You are already ingame")
  }

  def show(b: Boolean): Unit = println(Controller.build(b))

  def showAll(b: Boolean): Unit = println(Controller.buildAll(b))
}