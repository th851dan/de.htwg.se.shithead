package de.htwg.se.shithead.controller.controllerBase.controllerMock

import de.htwg.se.shithead.Util._
import de.htwg.se.shithead.controller.GameState._
import de.htwg.se.shithead.controller.{CellChanged, ControllerInterface}
import de.htwg.se.shithead.model.{CardStackInterface, User, UserListInterface}

import scala.swing.Publisher

class Controller(var userList:UserListInterface, var cardStack:CardStackInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManage
  var status: Status = BEFORESTART
  var userCount:Int = 0


  //Commands
  def remove(name: String): Boolean = false

  def add(name: String): Boolean = false

  def playCard(list2: List[Int]): Boolean = false

  def begin(): Boolean = false

  def setNextUser(): Unit = {}

  def changeCards(card1: Int, card2: Int): Boolean = false

  def compareToStartUser(): Unit = {}


  def getCard(i: Int): String = ""

  def checkList(list: List[Int]): Boolean = false

  def buildAll(b: Boolean): String = ""

  def build(u: User, b: Boolean): String = ""

  def getUserListLength(): Int = 1

  def build(b: Boolean): String = ""

  def getCurrentUserName(): String = ""

  def getPlayedCard(): String = ""

  def currentUserHasHand(): Boolean = false

  def undo(): Unit = {}

  def redo(): Unit = {}

}