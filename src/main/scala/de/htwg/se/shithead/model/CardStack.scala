package de.htwg.se.shithead.model

import scala.util.Random

object CardStack {

    val suites = Set(Spade, Heart, Club, Diamond)
    val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

    case class Stack(cardStack: List[Card] = for (r <- ranks; s <- suites) yield Card(r, s)) {
        
        var cards = if (isValidStack(cardStack)) cardStack
                    else throw new RuntimeException("Stack is invalid!")

        def shuffle() = new Stack(Random.shuffle(cards))

        def pullFromTop(): List[Card] = {
            val cardsHead = cards.head
            val cardTail = cards.tail

            cards = if (isValidStack(cardTail)) cardTail
                    else throw new RuntimeException("Stack is invalid!")
            List(cardsHead)
        }

        def addToTop(cardsToAdd: List[Card]) = new Stack(cardsToAdd ::: cards)

        private def isValidStack(cards: List[Card]) = cards.size <= 52 && cards.distinct.size == cards.size
    }

    var stack = new Stack

    def apply() = stack.shuffle

    def pullFromTop() = stack.pullFromTop

    def addToTop(cardsToAdd: List[Card]) = stack.addToTop(cardsToAdd)
}