package de.htwg.se.shithead.model

import scala.util.Random

object CardStack {

    val suites = Set(Spade, Heart, Club, Diamond)
    val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
    val cards: List[Card] = for(r <- ranks; s <- suites) yield Card(r, s, false)
    var cardStack = Stack(cards, true)
    var tableStack = Stack(List(), false)

    case class Stack(cards: List[Card], isVaild: Boolean) {

        val cardStack: List[Card]= if (isValidStack(cards) && isVaild || !isVaild) cards  else  throw new RuntimeException("Stack is invalid")
        
        def shuffle(): Stack = new Stack(Random.shuffle(cardStack), isVaild)

        def pullFromTop(): (Card, Stack) = (cardStack.head, new Stack(cardStack.tail, isVaild))

        def addToTop(card: Card): Stack = new Stack(card :: cardStack, isVaild)

        def addToTop(cardsToAdd: List[Card]): Stack = new Stack(cardsToAdd ::: cardStack, isVaild)

        def topElement():Card = cardStack(cardStack.length - 1)

        def isEmpty():Boolean = cardStack.length == 0
        
        private def isValidStack(cards: List[Card]): Boolean = cards.size <= 52 && cards.distinct.size == cards.size
    }

    def addToTop(card: Card) = cardStack = cardStack.addToTop(card)
    
    def addToTop(cardsToAdd: List[Card]) = cardStack = cardStack.addToTop(cardsToAdd)

    def shuffle() = cardStack = cardStack.shuffle

    def pullFromTop(): Card = {
        val tuple = cardStack.pullFromTop
        cardStack = tuple._2
        tuple._1
    }

    def getTopValue(card:Card): Card = {
        if(tableStack.cardStack.length == 0) tableStack = tableStack.addToTop(card)
        tableStack.topElement()
    }

    def playCard(list:List[Int]):Boolean = {
        val user: User = UserList.currentUser
        val size: Int = user.size()
        var b:Boolean = true

        if(list.length > size && !user.emptyHand() || list.length <= size) {
            val firstElement = user.getCard(list(0))
            var topCard = getTopValue(firstElement)

            if(topCard.rank.value > firstElement.rank.value) b = false
            for(i <- list)
                if(!user.getCard(i).equals(firstElement)) b = false
        } else  b = false
        user.checkAmountOfCards()
        if(user.userCardStackTable.length == 0) user.finished = true
        b
    }
}