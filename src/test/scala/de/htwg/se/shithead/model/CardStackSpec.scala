package de.htwg.se.shithead.model

import de.htwg.se.shithead.model.cardStackComponent.baseImp.CardStack
import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import org.scalatest.{Matchers, WordSpec}

class CardStackSpec extends WordSpec with Matchers {

  "A CardStack" when {

    "constructed with allCards and empty TableStack" should {
      var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)

      "have a non empty cardStack and empty Tablestack" in {
        cardStack.cardStack.isEmpty() should be(false)
        cardStack.tableStack.isEmpty() should be(true)
        cardStack.cardStack.isValid should be(true)
        cardStack.tableStack.isValid should be(false)
        cardStack.valid should be(true)
        cardStack.reverse should be(false)
      }
    }

    "default constructed and cards added and removed" should {
      var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)

      "remove cards from cardstack to tablestack" in {
        var tuple = cardStack.pullFromTopCardStack()
        cardStack = tuple._1
        cardStack = cardStack.addToTopTableStack(tuple._2)
        cardStack.tableStack.isEmpty should be(false)
        cardStack.cardStack.cardStack.length should be(51)
      }

      "delete TableStack" in {
        var tuple = cardStack.pullFromTopCardStack()
        cardStack = tuple._1
        cardStack = cardStack.addToTopTableStack(tuple._2)
        cardStack = cardStack.deleteTableStack()
        cardStack.tableStack.isEmpty should be(true)
      }
    }

  }

  def getCards(b: Boolean): Stack = {
    if (b) {
      val suites = Set(Spade, Heart, Club, Diamond)
      val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
      new Stack(for (r <- ranks; s <- suites) yield Card(r, s, false), true)
    } else new Stack(List(), false)
  }
}
