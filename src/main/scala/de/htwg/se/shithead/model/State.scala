package de.htwg.se.shithead.model

sealed abstract class State(val value: Int) {
  var status: State = _
  val statusList = List(BeforeStart, DuringStart, RunningGame)

  case object BeforeStart extends State()

  case object DuringStart extends State()

  case object RunningGame extends State()
  
  def setStatus(value: State): Unit = {
    if(statusList.contains(value)) {
      status = value
    } else {
      println("State doesn't exist!")
    }
  }
}