package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.model.UserListInterface
import de.htwg.se.shithead.model.baseImp.UserList

class setNextUserCommand(controller: ControllerInterface) extends Command{

  var uL:UserListInterface = controller.userList

  override def doStep(): Unit = {
    uL = controller.userList
    controller.userList = controller.userList.setNextUser()
  }

  override def undoStep(): Unit = {
    val newUL = controller.userList
    controller.userList = uL
    uL = newUL
  }

  override def redoStep(): Unit = {
    val newUL = controller.userList
    controller.userList = uL
    uL = newUL
  }
}
