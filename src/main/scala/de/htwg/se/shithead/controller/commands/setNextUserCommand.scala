package de.htwg.se.shithead.controller.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.Controller
import de.htwg.se.shithead.model.UserList

class setNextUserCommand(controller: Controller) extends Command{

  var uL:UserList = controller.userList

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
