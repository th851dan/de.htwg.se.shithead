package de.htwg.se.shithead.model.userListComponent.baseImp

import de.htwg.se.shithead.model.{Card, User, UserListInterface}

import scala.util.control.Breaks.{break, breakable}

case class UserList(userList: List[User], currentUser: User) extends UserListInterface {
  def this() = this(List(), User("", List(), List()))

  def addUser(name: String): UserList = copy(if (!isValid(name)) new User(name, List(), List()) :: userList else userList, currentUser)

  private def isValid(name: String): Boolean = userList.exists(_.name == name)

  def setCurrentUser(i: Int): UserList = copy(userList, userList(i))

  def removeUser(name: String): UserList = copy(if (isValid(name)) userList.filter(_.name != name) else userList, currentUser)

  def initialize(cardFalse: Card, cardHand: Card, cardTable: Card, user: User): UserList = {
    var ul: UserList = this
    for (_ <- 0 to 2) {
      var u: User = user
      var card = cardHand
      card = card.setVisibility(true)
      u = user.addTable(card)
      u = u.addTable(cardFalse)
      card = cardTable
      card = card.setVisibility(true)
      u.addHand(card)
      ul = ul.updateList(u)
    }
    ul
  }

  def setNextUser(): UserList = {
    var user: User = currentUser
    breakable {
      for (List(left, right) <- userList.sliding(2)) {
        if (left.NAME.equals(currentUser.NAME)) {
          user = right
          break()
        }
        else if (currentUser.NAME.equals(userList(userListLength() - 1).NAME)) {
          user = userList(0)
          break()
        }
      }
    }
    copy(userList, user)
  }

  def userListLength(): Int = userList.length

  def switchCards(c1: Int, c2: Int): UserList = if (!(c1 > 2 || c1 < 0 || c2 > 2 || c2 < 0)) {
    var user: User = currentUser
    val cardH = currentUser.userCardStackHand(c1)
    val cardT = getTrueTableCard(currentUser, c2)
    user = user.removeHand(cardH)
    user = user.removeTable(cardT)
    user = user.addHand(cardT)
    user = user.addTable(cardH)
    updateList(user)
  } else this

  def updateList(user: User): UserList = {
    var list: List[User] = List()
    userList.reverse.foreach(u => if (u.NAME == user.NAME) list = user :: list else list = u :: list)
    copy(list, if (user.equals(currentUser)) user else currentUser)
  }

  def getTrueTableCard(user: User, int: Int): Card = {
    var list: List[Card] = List()
    user.userCardStackTable.reverse.foreach(u => if (u.visibility == true) list = u :: list)
    list(int)
  }

}
