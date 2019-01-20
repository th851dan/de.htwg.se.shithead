package de.htwg.se.shithead.model

import de.htwg.se.shithead.model.cardStackComponent.baseImp.CardStack
import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import org.scalatest.{Matchers, WordSpec}

class CardStackSpec extends WordSpec with Matchers {

  "A CardStack" when {
    val card = new Card(Two, Spade, true)
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

      "remove cards from cardstack to tablestack" in {
        var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
        var tuple = cardStack.pullFromTopCardStack()
        cardStack = tuple._1
        cardStack = cardStack.addToTopTableStack(tuple._2)
        cardStack.tableStack.cardStack.length should be(1)
        cardStack.cardStack.cardStack.length should be(51)
        cardStack.addToTopTableStack(List(card)).tableStack.cardStack should be(2)
      }

      "delete TableStack" in {
        var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
        var tuple = cardStack.pullFromTopCardStack()
        cardStack = tuple._1
        cardStack = cardStack.addToTopTableStack(tuple._2)
        cardStack = cardStack.deleteTableStack()
        cardStack.tableStack.isEmpty should be(true)
      }

      "add to cardstack with addToTopCardStack" in {
        var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
        cardStack = cardStack.addToTopCardStack(card)
        cardStack = cardStack.addToTopCardStack(List(card))
        cardStack.cardStack.cardStack.length should be(54)

      }

    }

    "default constructed and shuffled" should {
      "shuffle cards randomly" in {
        var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
        cardStack = cardStack.shuffleCardStack()
        cardStack.cardStack should not be(getCards(true))
      }
    }

    "checklistlength and checkCardValue" should {
      var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
      "listlength with emptyhand be user cardstacktable length" in {
        cardStack = cardStack.checkListLength(new User("Hans",List(card),List(card)),0)
        cardStack.valid should be (true)
        cardStack = cardStack.checkListLength(new User("Hans",List(card),List(card)),1)
        cardStack.valid should be(false)
      }

      "listlength without emptyhand be user cardstacktable length" in {
        cardStack = cardStack.checkListLength(new User("Hans",List(),List(card)),0)
        cardStack.valid should be (true)
        cardStack = cardStack.checkListLength(new User("Hans",List(),List(card)),1)
        cardStack.valid should be (false)
      }

      "cardValue with 10 2 and 3 always be true" in {
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Two,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be(true)
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Three,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be(true)
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Ten,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be(true)
      }

      "cardValue with smaller then element,with bigger/same then element be " in {
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Jack,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be (false)
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Ace,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be (true)
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Seven,Spade,true),new Card(King,Spade,true))
        cardStack.valid should be (false)
        cardStack = cardStack.checkCardValue(new User("Hans",List(),List()),new Card(Seven,Spade,true),new Card(Five,Spade,true))
        cardStack.valid should be(true)
        cardStack.reverse should be(true)


      }

      "get Top table element" should {
        "give the top element of table " in {
          cardStack.getTopTableElement() should be(new NoSuchElementException)
          cardStack.addToTopTableStack(card)
          cardStack.getTopTableElement() should be(card)

        }
      }

      "set valid" should {
        "set valid to bool" in {
          cardStack = cardStack.setValid(false)
          cardStack.valid should be(false)
        }
      }
    }

    "remove card from user" should {
      var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
      var user = new User("hans",List(card),List(card))
      "remove card from userhand and if empty from table" in {
        user = cardStack.removeCard(card, user)
        user.emptyHand() should be(true)
        user = cardStack.removeCard(card, user)
        user.userCardStackTable.isEmpty should be(true)

      }
    }

    "playCard "  should {
      var cardStack = new CardStack(getCards(true),new Stack(List(),false),false, true)
      var user = new User("hans",List(card),List(new Card(Ten,Spade,true)))
      " play cards from user to table if valid" in {
        var tuple = cardStack.playCard(List(0),user)
        cardStack = tuple._1
        user = tuple._2
        user.emptyHand should be(true)
        cardStack.tableStack.isEmpty should be(false)
        tuple = cardStack.playCard(List(0),user)
        tuple._1.tableStack.isEmpty should be (true)
        tuple._2.userCardStackTable.isEmpty should be(true)


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
