package de.htwg.se.shithead.view

import de.htwg.se.shithead.controller.{CellChanged, ControllerInterface, GameState}
import de.htwg.se.shithead.controller.GameState._

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor{

  listenTo(controller)

  def matches(line: String): Boolean = line.toLowerCase.matches("((\\s)*y(\\s)+[123](\\s)+[123](\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
      "((\\s)*start(\\s)+game(\\s)*)|" +
      "((\\s)*play((\\s)+(\\d+\\s*){1,4}))|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)|u|r")

  def eval(line: String) {
    if (matches(line)) {
      line replaceFirst("^ *", "")
      val splitted = line.split("\\s+")
      splitted(0) = splitted(0).toLowerCase
      splitted(0) match {
        case "y" => if (controller.changeCards((splitted(1)toInt), (splitted(2)toInt))) println("success") else println("Operation failed")
        case "n" => {
          controller.setNextUser()
          controller.compareToStartUser()
        }
        case "start" => if(!controller.begin()) println("Operation failed")
        case "add" => if(!controller.add(splitted(2))) println("Operation failed ")
        case "play" => {
          var list: List[Int] = List()
          for (i <- 1 to splitted.length - 1) list = splitted(i).toInt - 1 :: list
          if(!controller.playCard(list)) println("Operation failed")
        }
        case "remove" => if(!controller.remove(splitted(2))) println("Operation failed")
        case "q" => println("Adios Amigos\n")
        case "r" => controller.redo()
        case "u" => controller.undo()
      }
    } else println("wrong syntax\n")
  }


  def show(b: Boolean): Unit = println(controller.build(b))

  def showAll(b: Boolean): Unit = println(controller.buildAll(b))

  reactions += {
    case event:CellChanged => printTui()
  }

  def printTui(): Boolean = controller.status match {
    case BEFORESTART => {
      println(GameState.answer(BEFORESTART))
      true
    }
    case USERREMOVED => {
      println(GameState.answer(USERREMOVED))
      controller.status = BEFORESTART
      true
    }
    case PLAYEDCARDS => {
      print(GameState.answer(PLAYEDCARDS))
      if (!controller.cardStack.tableStack.isEmpty()) {
        println(controller.getPlayedCard())
      }
      else println("a 10 or 4 of same kind")
      controller.status = DURINGGAME
      show(true)
      println(controller.getCurrentUserName() + " it's your turn")
      true
    }
    case STARTGAME => {
      println(GameState.answer(STARTGAME))
      true
    }
    case PICKEDUPCARDS => {
     println(GameState.answer(PICKEDUPCARDS))
      showAll(true)
      controller.status = DURINGGAME
      true
    }
    case BEGIN => {
      println(controller.getCurrentUserName() + " " + GameState.answer(BEGIN))
      show(true)
      true
    }
    case DURINGGAME => {
      println(GameState.answer(DURINGGAME))
      show(true)
      true
    }
    case FINISHED => {
      println(GameState.answer(FINISHED))
      true
    }
    case GAMESTARTS => {
      show(false)
      println(controller.getCurrentUserName() + GameState.answer(GAMESTARTS))
      controller.status = DURINGGAME
      true
    }
  }
}