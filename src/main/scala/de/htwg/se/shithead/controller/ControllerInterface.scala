package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.{CardStackInterface, User, UserListInterface}

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  var userList:UserListInterface
  var cardStack:CardStackInterface
  var status: GameState.Status
  var userCount:Int

  //Command
  def remove(name: String): Boolean

  def add(name: String): Boolean

  def playCard(list2: List[Int]): Boolean

  def begin(): Boolean

  def setNextUser(): Unit
  def changeCards(card1: Int, card2: Int): Boolean

  def compareToStartUser(): Unit


  def getCard(i: Int): String

  def checkList(list: List[Int]): Boolean

  def buildAll(b: Boolean): String

  def build(u: User, b: Boolean): String

  def getUserListLength(): Int

  def build(b: Boolean): String

  def getCurrentUserName(): String

  def getPlayedCard(): String

  def currentUserHasHand(): Boolean

  def undo(): Unit

  def redo(): Unit

  def save: Unit

  def load: Unit
}
