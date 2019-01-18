package de.htwg.se.shithead.model.mockImp

import de.htwg.se.shithead.model._

case class UserList(userList: List[User], currentUser: User) extends UserListInterface{

  def userListLength(): Int = 1

  def addUser(name: String): UserList = this

  def setCurrentUser(i: Int): UserList = this

  def removeUser(name: String): UserList = this

  private def isValid(name: String): Boolean = false

  def initialize(cardFalse:Card, cardHand:Card, cardTable:Card, user:User): UserList = this

  def updateList(user: User): UserList = this

  def setNextUser(): UserList = this

  def getTrueTableCard(user: User, int: Int): Card = new Card(Two,Spade,false)

  def switchCards(c1: Int, c2: Int): UserList = this
}
