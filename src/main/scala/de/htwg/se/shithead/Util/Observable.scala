package de.htwg.se.shithead.Util

trait Observer {
  def update(): Boolean
}

class Observable {
  var subs: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subs = subs :+ s

  def remove(s: Observer): Unit = subs = subs.filterNot(o => o == s)

  def notifyObservers(): Unit = subs.foreach(o => o.update)
}