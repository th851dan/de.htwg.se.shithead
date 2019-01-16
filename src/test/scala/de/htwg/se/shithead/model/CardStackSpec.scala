package de.htwg.se.shithead.model

import org.scalatest._

class CardStackSpec extends WordSpec with Matchers {
    "A CardStack" can {
        val cardStack = CardStack
        val tmp = cardStack.cardStack.cardStack(0)
        val card = cardStack.pullFromTopCardStack
        "pullFromTopCardStack is called" should {
            "pull firt element of cardStack" in {
                tmp should be(card)
            }/*
            "remove card from cardStack" in {
                cardStack.cardStack.cardStack(0) == card should be(false)
            }*/
        }
        "addToTopCardStack is called with argument single card" should {
            cardStack.addToTopCardStack(card)
            "add card to top of cardStack" in {
                cardStack.cardStack.cardStack(0) should be(card)
            }
        }
        val card1 = cardStack.pullFromTopCardStack
        val card2 = cardStack.pullFromTopCardStack
        val list = List(card1, card2)
        "addToTopCardStack is called with argument list of cards" should {
            cardStack.addToTopCardStack(list)
            "add list to top of cardStack" in {
                cardStack.cardStack.cardStack(0) should be(card1)
                cardStack.cardStack.cardStack(1) should be(card2)
            }
        }
    }
}