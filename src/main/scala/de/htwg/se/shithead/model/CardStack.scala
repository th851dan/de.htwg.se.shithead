package de.htwg.se.shithead.model

import scala.util.Random

object CardStack {
    val suites = Set(Spade, Heart, Club, Diamond)
    val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

    class Stack(cardStack: List[Card] = for (r <- ranks; s <- suites) yield Card(r, s)) {
        val cards = if (isValidStack(cardStack)) cardStack
        else throw new RuntimeException("Stack is invalid!")

        def shuffle() = new Stack(Random.shuffle(cards))

        def pullFromTop() = (cards.head, new Stack(cards.tail))

        def addToTop(card: Card) = new Stack(card :: cards)

        def addToTop(cardsToAdd: List[Card]) = new Stack(cardsToAdd ::: cards)

        private def isValidStack(cards: List[Card]) = cards.size <= 52 && cards.distinct.size == cards.size
    }
}