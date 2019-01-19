package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.util.Command
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.model.{CardStackInterface, User, UserListInterface}
import de.htwg.se.shithead.controller.GameState._

class playCommand(controller: ControllerInterface, list2: List[Int]) extends Command{

  var usLi: UserListInterface = controller.userList
  var caSt: CardStackInterface = controller.cardStack
  var state:Status = controller.status

  override def doStep(): Unit = {
    usLi = controller.userList
    caSt = controller.cardStack
    state = controller.status

    val list:List[Int] = list2.distinct.reverse
    val firstElement = controller.userList.currentUser.getCard(list(0))

    controller.cardStack = controller.cardStack.setValid(controller.cardStack.checkEquality(list, controller.userList.currentUser, firstElement))
    controller.cardStack = controller.cardStack.checkListLength(controller.userList.currentUser, list.length)
    if (controller.cardStack.tableStack.cardStack.length != 0) controller.cardStack = controller.cardStack.checkCardValue(controller.userList.currentUser, firstElement, controller.cardStack.getTopTableElement())
    addCardsToHand(controller.userList.currentUser)
    playCard(list, controller.userList.currentUser)
    checkAmountOfCards(controller.userList.currentUser)
    setNextUser(firstElement.rank.value)
    if(controller.userList.currentUser.stackTable.isEmpty && controller.userList.currentUser.stackTable.isEmpty) controller.status = FINISHED
  }

  override def undoStep(): Unit = {
    val newUsLi = controller.userList
    val newCaSt = controller.cardStack
    val newState = controller.status
    controller.userList = usLi
    controller.cardStack = caSt
    controller.status = state
    caSt = newCaSt
    usLi = newUsLi
    state = newState
  }

  override def redoStep(): Unit = {
    val newUsLi = controller.userList
    val newCaSt = controller.cardStack
    val newState = controller.status
    controller.userList = usLi
    controller.cardStack = caSt
    controller.status = state
    caSt = newCaSt
    usLi = newUsLi
    state = newState
  }

  private def setNextUser(value: Int): Unit = value match {
    case 10 => if (!controller.cardStack.valid) controller.userList = controller.userList.setNextUser()
    case 8 => if (controller.cardStack.valid) for(_ <- 0 to 1) controller.userList = controller.userList.setNextUser()
    case _ => if (controller.cardStack.valid) controller.userList = controller.userList.setNextUser()
  }

  private def playCard(list:List[Int], user:User): Unit = if(controller.cardStack.valid) {
    val tuple = controller.cardStack.playCard(list, user)
    controller.cardStack = tuple._1
    controller.userList = controller.userList.updateList(tuple._2)
    controller.status = PLAYEDCARDS
  }

  private def checkAmountOfCards(user:User): Unit = {
    var u:User = user
    while (u.userCardStackHand.length < 3 && !controller.cardStack.cardStack.isEmpty()) {
      val tuple = controller.cardStack.pullFromTopCardStack();
      controller.cardStack = tuple._1
      u = u.addHand(tuple._2)
    }
    controller.userList = controller.userList.updateList(u)
  }

  private def addCardsToHand(user: User): Unit = if(!controller.cardStack.valid) {
    controller.userList = controller.userList.updateList(user.addHand(controller.cardStack.tableStack.cardStack))
    controller.cardStack = controller.cardStack.deleteTableStack()
    controller.status = PICKEDUPCARDS
  }

}
