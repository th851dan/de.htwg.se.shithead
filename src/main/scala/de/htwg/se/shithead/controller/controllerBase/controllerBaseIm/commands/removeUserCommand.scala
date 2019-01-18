package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.controller.GameState._
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.model.UserListInterface
import de.htwg.se.shithead.model.baseImp.UserList

class removeUserCommand(name:String, controller:ControllerInterface) extends Command{
  var uL:UserListInterface = controller.userList
  var removed:Boolean = false

  override def doStep(): Unit = {
    uL = controller.userList
    controller.userList = controller.userList.removeUser(name)
    if(!controller.userList.equals(uL)) {
      removed = true
      controller.status = USERREMOVED
    }

  }

  override def redoStep(): Unit = {
    val newUL = controller.userList
    controller.userList = uL
    uL = newUL
  }

  override def undoStep(): Unit = {
    val newUL = controller.userList
    controller.userList = uL
    uL = newUL
  }
}
