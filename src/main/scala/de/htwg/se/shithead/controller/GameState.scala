package de.htwg.se.shithead.controller

object GameState extends Enumeration {

  type Status = Value
  val BEFORESTART,USERREMOVED,STARTGAME,BEGIN,GAMESTARTS,DURINGGAME,PICKEDUPCARDS,PLAYEDCARDS,FINISHED,SAVED,LOADED,COUDNTLOAD = Value
  val map = Map[Status, String](
    BEFORESTART -> "new user added",
    STARTGAME -> "Game starts: ",
    BEGIN -> "you want to swap cards? y ID1 ID2 / n",
    GAMESTARTS -> " you start off first! You can play any card with play ID (UP TO 4 TIMES)",
    DURINGGAME -> "your cards:",
    PICKEDUPCARDS -> "you have to pick up cards :(",
    PLAYEDCARDS -> "played card: ",
    FINISHED -> "GAMER OVER",
    USERREMOVED -> "User removed.",
    SAVED -> "game save",
    LOADED -> "game loaded",
    COUDNTLOAD -> "couldn't load"
  )

  def answer(stat: Status): String = map(stat)
}
