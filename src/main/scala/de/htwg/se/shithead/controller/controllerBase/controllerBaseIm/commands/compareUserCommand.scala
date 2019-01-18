package de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands

import de.htwg.se.shithead.Util.Command
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.controller.GameState._

class compareUserCommand(controller:ControllerInterface) extends Command {
  var state: Status = controller.status

  override def doStep(): Unit = {
    state = controller.status
    if(controller.userList.userList(0).equals(controller.userList.currentUser))
      controller.status = GAMESTARTS
  }

  override def undoStep(): Unit = {
    val newState = controller.status
    controller.status = state
    state = newState
  }

  override def redoStep(): Unit = {
    val newState = controller.status
    controller.status = state
    state = newState
  }
}
