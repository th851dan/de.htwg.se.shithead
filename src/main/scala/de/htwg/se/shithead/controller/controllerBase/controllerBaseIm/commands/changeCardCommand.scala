package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.controller.GameState._
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.model.UserListInterface
import de.htwg.se.shithead.model.baseImp.UserList

class changeCardCommand(card1: Int, card2: Int,controller: ControllerInterface)extends Command{

  var uL:UserListInterface = controller.userList
  var changed:Boolean = true
  var state: Status = controller.status

  override def doStep(): Unit = {
    uL = controller.userList
    state = controller.status
    changed = card1 >= 0 || card1 <= 2 || card2 >= 0 || card2 <= 2
    controller.userList = controller.userList.switchCards(card1,card2)
  }

  override def redoStep(): Unit = {
    val newUL = controller.userList
    val newState = controller.status
    controller.userList = uL
    controller.status = state
    uL = newUL
    state = newState
  }

  override def undoStep(): Unit = {
    val newUL = controller.userList
    val newState = controller.status
    controller.userList = uL
    controller.status = state
    uL = newUL
    state = newState
  }
}
