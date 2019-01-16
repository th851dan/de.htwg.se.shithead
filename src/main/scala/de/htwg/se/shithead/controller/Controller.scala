package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.{CardStack, User, UserList}

object Controller {
  //status
  var status: Int = 0

  def checkList(list: List[Int]): Boolean = {
    var b:Boolean = true
    list.foreach(u => if(UserList.currentUser.userCardStackHand.length - 1 < u) b = false else if(UserList.currentUser.emptyHand()) if(UserList.currentUser.stackTable.length - 1 < u) b = false)
    b
  }

  def getCard(i: Int): String = UserList.currentUser.getCard(i).toString

  def buildAll(b: Boolean): String = {
    val sb = new StringBuilder()
    for (u <- UserList userList)
      sb.append(build(u, b))
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

  def begin(): Unit = UserList.initialize()

  def build(b: Boolean): String = build(getCurrentUser(), b)

  def remove(name: String): Boolean = UserList.removeUser(name)

  def add(name: String): Boolean = UserList.addUser(name)

  def getState(): Int = this.status

  def setState(status: Int): Unit = this.status = status

  def setNextUser() =  UserList.setNextUser()

  def changeCards(card1: Int, card2: Int): String = UserList.switchCards(card1 - 1, card2 - 1)

  def compareToStartUser(): Boolean = getCurrentUser.equals(UserList.userList(0))

  def getCurrentUser(): User = UserList.currentUser

  def getTurn(): String = getCurrentUserName + " It's your turn: "

  def getCurrentUserName(): String = UserList.currentUser.NAME

  def playCard(list: List[Int]): Boolean = CardStack.playCard(list)

  def getPlayedCard(): String = CardStack.tableStack.cardStack.head.toString

  def getRank(): Int = UserList.getRank()

  def currentUserHasHand(): Boolean = this.getCurrentUser.userCardStackHand.length > 2
}