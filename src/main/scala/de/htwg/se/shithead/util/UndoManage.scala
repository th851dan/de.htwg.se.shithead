package de.htwg.se.shithead.util

class UndoManage {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): Unit = {
    undoStack = command :: undoStack
    command.doStep()
  }

  def undoStep(): Unit = undoStack match {
    case head :: stack => {
      head.undoStep()
      undoStack = stack
      redoStack = head :: redoStack
    }
    case Nil =>
  }

  def redoStep(): Unit = redoStack match {
    case head :: stack => {
      head.redoStep()
      redoStack = stack
      undoStack = head :: undoStack
    }
    case Nil =>
  }
}
