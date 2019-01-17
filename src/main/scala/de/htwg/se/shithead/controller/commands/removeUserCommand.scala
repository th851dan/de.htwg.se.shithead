package de.htwg.se.shithead.controller.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.Controller
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.controller.GameState._

class removeUserCommand(name:String, controller:Controller) extends Command{
  var uL:UserList = controller.userList
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
