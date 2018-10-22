package de.htwg.se.shithead.model

case class User(name: String) {
    val UUID: String = java.util.UUID.randomUUID.toString
    val NAME: String = name
    var userCardStackHand: List[Card] = List()
    var userCardStackTable: List[Card] = List()

    def addHand(card : Card) = {
       userCardStackHand = card :: userCardStackHand
    }

    def addHand(list : List[Card]) = {
        userCardStackHand = list ::: userCardStackHand
    }

    def removeHand(card : Card) = {
        userCardStackHand = userCardStackHand.filter(_ equals card)
    }

    def addTable(card : Card) = {
        userCardStackTable = card :: userCardStackTable 
    }

    def removeTable(card : Card) = {
        userCardStackHand = userCardStackTable.filter(_ equals card)
    }
}