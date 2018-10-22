package de.htwg.se.shithead.model

case class User(name: String) {
    val UUID: String = java.util.UUID.randomUUID.toString
    val NAME: String = name
    var userCardStackHand: List[Card] = List()
    var userCardStackTable: List[Card] = List()
}