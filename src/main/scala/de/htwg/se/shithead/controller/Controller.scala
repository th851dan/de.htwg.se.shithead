package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.{CardStack, User, UserList}

object Controller {
  //status
  var status: Int = 0

  def buildAll(b: Boolean): String = {
    val sb = new StringBuilder()
    for (u <- UserList userList) {
      sb.append(build(u, b))
    }
    sb.toString
  }


  def build(u: User, b: Boolean): String = {
    var user: User = u
    val sb: StringBuilder = new StringBuilder()
    var i: Int = 0
    if (u.userCardStackHand.size != 0) {
      sb.append(u.name + ": \nCards on your hand: \n")
      for (card <- u.userCardStackHand) {
        if (i % 3 == 0 && i != 0) sb.append("\n")
        sb.append(card + " ")
        i += 1;
      }
    }
    if (b) {
      sb.append("\n\nCards on the table: \n")
      i = 0
      for (card <- u.userCardStackTable) {
        if (!card.visibility) i += 1;
        else sb.append(card + " ")
      }
      sb.append("\nand " + i + " face-down cards\n\n")
    } else {
      sb.append("\n\n")
    }
    sb.toString()
  }

  def getUserListLength(): Int = UserList.userListLength()

  def begin() = UserList.initialize()

  def checkIfLastCardWasATen(): Boolean = CardStack.tableStack.topElement().rank.value == 10

  def build(b: Boolean): String = build(getCurrentUser(), b)

  def remove(name: String): Boolean = UserList.removeUser(name)

  def add(name: String): Boolean = UserList.addUser(name)

  def getState() = this status

  def setState(status: Int) = this status = status

  def setNextUser() = UserList.getNextUser()

  def changeCards(card1: Int, card2: Int): String = UserList.switchCards(card1 - 1, card2 - 1)

  def compareToStartUser(): Boolean = getCurrentUser.equals(UserList.userList(0))

  def getTurn(): String = getCurrentUserName + " It's your turn: "

  def getCurrentUserName(): String = UserList.currentUser.NAME

  def playCard(list: List[Int]): Boolean = CardStack.playCard(list)

  def getPlayedCard(card1: Int): String = getCurrentUser.getCard(card1).toString()

  def getCurrentUserCardStackLength() = getCurrentUser.size()

  def hasFinished(): Boolean = getCurrentUser().hasFinished()

  def getCurrentUser(): User = UserList.currentUser

  def getRank(): Int = UserList.getRank()
}