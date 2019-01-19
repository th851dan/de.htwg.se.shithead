package de.htwg.se.shithead.model

import org.scalatest.{Matchers, WordSpec}

class UserSpec extends WordSpec with Matchers {
  "An User" when {
    val card = new Card(Two, Spade, true)
    val card2 = new Card(Three, Spade, true)
    val cardList = List(new Card(Two, Spade, true))

    "constructed without parameter" should {
      val user = new User("Hans", List(), List())
      "have name and emptylists" in {
        user.name should be("Hans")
        user.stackTable.isEmpty should be(true)
        user.stackHand.isEmpty should be(true)
        user.emptyHand() should be(true)
      }
    }

    "add to Hand and Table with list and with normal card and getcard" should {
      var user = new User("Hans", List(), List())
      var user2 = user
      user = user.addHand(card)
      user2 = user.addHand(cardList)
      user = user.addTable(card)
      "with name and list with card two spade true" in {
        user.userCardStackTable(0) should be(card)
        user2.userCardStackHand(0) should be(card)
        user.userCardStackHand(0) should be (card)
        user.getCard(0) should be (card)
      }
    }

    "remove from hand " should {
      var user = new User("Hans", List(card), List(card2))
      user = user.removeHand(card)
      "with name and list with card three spade true" in {
        user.getCard(0) should be (card2)
      }
    }

    "remove from table " should {
      var user = new User("Hans", List(), List(card2))
      user = user.removeTable(card2)
      "with name and list with card two spade true" in {
        user.userCardStackTable should be (List())
      }
    }

  }
}