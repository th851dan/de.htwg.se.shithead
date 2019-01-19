package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller._
import de.htwg.se.shithead.model._
import de.htwg.se.shithead.controller.GameState._
import de.htwg.se.shithead.model.userListComponent.baseImp.UserList

class iniCommand(controller: ControllerInterface) extends Command{

  var cS:CardStackInterface = controller.cardStack
  var uL:UserListInterface = controller.userList
  var state:Status = controller.status


  override def doStep(): Unit = {
    cS = controller.cardStack
    uL = controller.userList
    state = controller.status

    controller.status = BEGIN

    var userl:List[User] = controller.userList.userList
    userl.foreach(u => {
      var cardList: List[Card] = List()
      var user:User = u
      for (i <- 0 to 8) {
        val tuple = controller.cardStack.pullFromTopCardStack()
        controller.cardStack = tuple._1
        if(i % 3 != 0) {
          cardList = tuple._2.setVisibility(true) :: cardList
        }
        else cardList = tuple._2 :: cardList
      }
      var count = 0
      cardList.foreach(c => {
        if (c.visibility && count % 2 == 0) {
          count +=1
          user = user.addHand(c)
        } else if (c.visibility && count % 2 != 0) {
          count +=1
          user = user.addTable(c)
        } else {
          user = user.addTable(c)
        }
      })
      controller.userList = controller.userList.updateList(user)
    })
    controller.userList = new UserList(controller.userList.userList, controller.userList.userList(0))
  }

  override def undoStep(): Unit = {
    val newUsLi = controller.userList
    val newCaSt = controller.cardStack
    val newState = controller.status
    controller.userList = uL
    controller.cardStack = cS
    controller.status = state
    cS = newCaSt
    uL = newUsLi
    state = newState
  }

  override def redoStep(): Unit = {
    val newUsLi = controller.userList
    val newCaSt = controller.cardStack
    val newState = controller.status
    controller.userList = uL
    controller.cardStack = cS
    controller.status = state
    cS = newCaSt
    uL = newUsLi
    state = newState
  }
}
