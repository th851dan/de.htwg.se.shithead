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
        userCardStackHand = userCardStackHand.filter(_ != card)
    }

    def addTable(card : Card) = {
        userCardStackTable = card :: userCardStackTable 
    }

    def removeTable(card : Card) = {
        userCardStackTable = userCardStackTable.filter(_ != card)
    }

    def size(): Int = {
        if(userCardStackHand.length == 0) userCardStackTable.length 
        else userCardStackHand.length
    }

    def getCard(id:Int):Card = {
        if(userCardStackHand.length == 0) userCardStackTable(id)
        else userCardStackTable(id)
    }
    
    def isHand(): Boolean = userCardStackHand != 0
}