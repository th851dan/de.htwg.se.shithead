package de.htwg.se.shithead.controller.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.Controller
import de.htwg.se.shithead.model.UserList

class addUserCommand(name:String, controller:Controller) extends Command{
  var uL:UserList = controller.userList
  var added:Boolean = true

  override def doStep(): Unit = {
    uL = controller.userList
    controller.userList = controller.userList.addUser(name)
    if(controller.userList.equals(uL)) added = false
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
