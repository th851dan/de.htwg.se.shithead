package de.htwg.se.shithead.model

import org.scalatest._

class UserSpec extends WordSpec with Matchers {
    "An User" when {
        val user = User("test")
        "created with name" should {
            "have name test" in {
                user.NAME should be("test")
            }
            "have uuid not empty" in {
                user.UUID.isEmpty should be(false)
            }
            "have finished false" in {
                user.finished should be(false)
            }
            "have userCardStackHand empty" in {
                user.userCardStackHand.isEmpty should be(true)
            }
            "have userCardStackTableempty" in {
                user.userCardStackTable.isEmpty should be(true)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called addHand with argument list of cards" should {
            val list = List(Card(Three, Spade, false), Card(Seven, Diamond, false))
            user.addHand(list)
            "add list to userCardStackHand" in {
                user.userCardStackHand.containsSlice(list) should be(true)
            }
            "not be empty" in {
                user.userCardStackHand.isEmpty should be(false)
            }
        }
        "called addHand with argument single card" should {
            val card = Card(Two, Spade, false)
            user.addHand(card)
            "add card to userCardStackHand" in {
                user.userCardStackHand.contains(card) should be(true)
            }
            "not be empty" in {
                user.userCardStackHand.isEmpty should be(false)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called removeHand with argument card" should {
            val list = List(Card(Three, Spade, false), Card(Seven, Diamond, false))
            user.addHand(list)
            val card = list(0)
            user.removeHand(card)
            "remove card from userCardStackHand" in {
                user.userCardStackHand.contains(Card) should be(false)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called addTable with argument card" should {
            val card = Card(Seven, Diamond, false)
            user.addTable(card)
            "add card to userCardStackTable" in {
                user.userCardStackTable.contains(card) should be(true)
            }
            "not be empty" in {
                user.userCardStackTable.isEmpty should be(false)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called removeTable with argument card" should {
            val card = Card(Seven, Diamond, false)
            user.addTable(card)
            user.removeTable(card)
            "remove card from userCardStackTable" in {
                user.userCardStackTable.contains(Card) should be(false)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called emptyHand" should {
            val returnVal = user.emptyHand
            "should return true if empty" in {
                returnVal should be(true)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called emptyHand" should {
            val returnVal = user.emptyHand
            user.addHand(Card(Two, Spade, false))
            "should return false if not empty" in {
                returnVal should be(false)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called hasFinished" should {
            user.finished = true
            val returnVal = user.hasFinished
            "should return true if user has finished" in {
                returnVal should be(true)
            }
        }
    }
/*
    "An User" when {
        val user = User("test")
        "called checkAmountOfCards" should {
            val list = List(Card(Three, Spade, false), Card(Seven, Diamond, false))
            user.addHand(list)
            val returnVal = user.checkAmountOfCards
            "should return true if user has finished" in {
                returnVal should be(2)
            }
        }
    }
*/
    "An User" when {
        val user = User("test")
        "called size" should {
            val list = List(Card(Three, Spade, false), Card(Seven, Diamond, false))
            user.addHand(list)
            val returnVal = user.size
            "should return 2 if user has cards in userCardStackHand" in {
                returnVal should be(2)
            }
        }
    }
    "An User" when {
        val user = User("test")
        "called size" should {
            val card = Card(Three, Spade, false)
            user.addHand(card)
            val returnVal = user.size
            "should return 1 if user has no cards in userCardStackHand" in {
                returnVal should be(1)
            }
        }
    }
}