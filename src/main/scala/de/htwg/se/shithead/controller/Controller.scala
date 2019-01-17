package de.htwg.se.shithead.controller

import de.htwg.se.shithead.Util._
import de.htwg.se.shithead.controller.commands._
import de.htwg.se.shithead.model.{CardStack, User, UserList}
import de.htwg.se.shithead.controller.GameState._

class Controller(var userList:UserList, var cardStack:CardStack) extends Observable {

  private val undoManager = new UndoManage
  var status: Status = BEFORESTART
  var userCount:Int = 0


  //Commands
  def remove(name: String): Boolean = if (status == BEFORESTART && userList.userListLength() != 0) {
      val command: removeUserCommand = new removeUserCommand(name, this)
      undoManager.doStep(command)
      if(command.removed) notifyObservers
      command.removed
    } else false

  def add(name: String): Boolean = if(status == BEFORESTART) {
      val command: addUserCommand = new addUserCommand(name, this);
      undoManager.doStep(command)
      notifyObservers
      command.added
    } else false

  def playCard(list2: List[Int]): Boolean = if(status == DURINGGAME) {
      val command: playCommand = new playCommand(this, list2)
      undoManager.doStep(command)
      notifyObservers
      true
    } else false

  def begin(): Boolean = if(status == BEFORESTART && userList.userListLength() >= 2) {
    undoManager.doStep(new iniCommand(this))
    notifyObservers
    true
  } else false

  def setNextUser(): Unit = if(status == BEGIN) {
    undoManager.doStep(new setNextUserCommand(this))
    if(!(userCount >= userList.userListLength()- 1))notifyObservers
    userCount += 1
  }

  def changeCards(card1: Int, card2: Int): Boolean = if(status == BEGIN) {
    val command:changeCardCommand = new changeCardCommand(card1 - 1, card2 - 1,this)
    undoManager.doStep(command)
    if(command.changed) notifyObservers
    command.changed
  } else false

  def compareToStartUser(): Unit = {
    undoManager.doStep(new compareUserCommand(this))
    if(status != BEGIN) notifyObservers()
  }


  def getCard(i: Int): String = userList.currentUser.getCard(i).toString

  def checkList(list: List[Int]): Boolean = {
    var b:Boolean = true
    list.foreach(u => if(userList.currentUser.userCardStackHand.length - 1 < u) b = false else if(userList.currentUser.emptyHand()) if(userList.currentUser.stackTable.length - 1 < u) b = false)
    b
  }

  def buildAll(b: Boolean): String = {
    val sb = new StringBuilder()
    for (u <- userList.userList)
      sb.append(build(u, b) + "\n")
    sb.toString
  }

  def build(u: User, b: Boolean): String = {
    val sb: StringBuilder = new StringBuilder()
    var i: Int = 0
    if (u.userCardStackHand.size != 0) {
      sb.append(u.name + " cards on your hand: \n")
      for (card <- u.userCardStackHand) {
        sb.append((i + 1) + "." + card + " ")
        sb.append("\n")
        i += 1;
      }
    }
    if (b) {
      sb.append("\nCards on the table: \n")
      i = 0
      var count = 1
      for (card <- u.userCardStackTable)
        if (!card.visibility) i += 1
        else {
          sb.append((count) + "." + card + "\n")
          count = count + 1
        }
      sb.append("and " + i + " face-down cards\n")
    } else sb.append("\n\n")
    sb.toString()
  }

  def getUserListLength(): Int = userList.userListLength()

  def build(b: Boolean): String = build(getCurrentUser(), b)

  private def getCurrentUser(): User = userList.currentUser

  def getCurrentUserName(): String = userList.currentUser.NAME

  def getPlayedCard(): String = cardStack.tableStack.cardStack.head.toString

  def currentUserHasHand(): Boolean = this.getCurrentUser.userCardStackHand.length > 2


  def undo(): Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }
}