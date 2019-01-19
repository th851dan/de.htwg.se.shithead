package de.htwg.se.shithead.util

trait Command {
  def doStep(): Unit

  def undoStep(): Unit

  def redoStep(): Unit
}