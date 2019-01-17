package de.htwg.se.shithead.Util

trait Command {
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit
}