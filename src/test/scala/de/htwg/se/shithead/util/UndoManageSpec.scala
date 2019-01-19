package de.htwg.se.shithead.util

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.shithead.util.UndoManage

class UndoManageSpec extends WordSpec with Matchers {

  "UndoManger" should {
    val undoManager = new UndoManage

    "have methods undo redo and do " in {
      val c = new testCommand
      c.i should be('a')
      undoManager.doStep(c)
      c.i should be('b')
      undoManager.undoStep()
      c.i should be('a')
      undoManager.redoStep()
      c.i should be('b')
    }

    "methods shouldnt do sth without step first" in {
      val c = new testCommand
      undoManager.redoStep()
      c.i should be('a')
      undoManager.undoStep()
      c.i should be('a')

    }
  }

}
