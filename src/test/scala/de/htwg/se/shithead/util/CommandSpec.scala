package de.htwg.se.shithead.util

import org.scalatest.{Matchers, WordSpec}

class CommandSpec extends WordSpec with Matchers {
  "A command" should {
    "have a undo step" in {
      val c = new testCommand()
      c.i should be('a')
      c.doStep()
      c.i should be('b')
      c.undoStep()
      c.i should be('a')
    }

    "have a do step" in {
      val c = new testCommand()
      c.i should be('a')
      c.doStep()
      c.i should be('b')
    }

    "have a redo step" in {
      val c = new testCommand()
      c.i should be('a')
      c.doStep()
      c.i should be('b')
      c.undoStep()
      c.i should be('a')
      c.redoStep()
      c.i should be('b')


    }
  }
}


class testCommand extends Command {
  var i = 'a'

  override def doStep(): Unit = i = (i + 1).toChar

  override def undoStep(): Unit = i = (i - 1).toChar

  override def redoStep(): Unit = i = (i + 1).toChar
}