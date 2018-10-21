package de.htwg.se.shithead.model

import scala.util.Random

object CardStack {

    val suites = Set(Spade, Heart, Club, Diamond)
    val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
    val cards: List[Card] = for(r <- ranks; s <- suites) yield Card(r, s, false)
    val cardStack = new Stack(cards)

    case class Stack(cards: List[Card]) {

        val cardStack = if (isValidStack(cards)) cards else throw new RuntimeException("Stack is invalid")
        
        def shuffle(): Stack = new Stack(Random.shuffle(cardStack))

        def pullFromTop(): (Card, Stack) = (cardStack.head, new Stack(cardStack.tail))

        def addToTop(card: Card): Stack = new Stack(card :: cardStack)

        def addToTop(cardsToAdd: List[Card]): Stack = new Stack(cardsToAdd ::: cardStack)

        private def isValidStack(cards: List[Card]): Boolean = cards.size <= 52 && cards.distinct.size == cards.size
    }

    def shuffle() = cardStack.shuffle
}