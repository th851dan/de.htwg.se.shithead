package de.htwg.se.shithead.model.cardStackComponent.baseImp

import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import de.htwg.se.shithead.model.{Card, CardStackInterface, User}

case class CardStack(cardStack: Stack, tableStack: Stack, reverse: Boolean, valid: Boolean) extends CardStackInterface {

  def deleteTableStack(): CardStack = copy(cardStack, tableStack.delete(), false, valid)

  def addToTopCardStack(card: Card): CardStack = copy(cardStack.addToTop(card), tableStack, reverse, valid)

  def addToTopCardStack(cardlist: List[Card]): CardStack = copy(cardStack.addToTop(cardlist), tableStack, reverse, valid)

  def addToTopTableStack(card: Card): CardStack = copy(cardStack, tableStack.addToTop(card), reverse, valid)

  def shuffleCardStack(): CardStack = copy(cardStack.shuffle(), tableStack, reverse, valid)

  def checkEquality(list: List[Int], user: User, card: Card): Boolean = {
    var b: Boolean = true
    list.foreach(i => if (user.getCard(i).rank.value != card.rank.value) false)
    b
  }

  def checkListLength(user: User, length: Int): CardStack = if (valid) user.emptyHand() match {
    case true => copy(cardStack, tableStack, reverse, user.userCardStackTable.length >= length)
    case false => copy(cardStack, tableStack, reverse, user.userCardStackHand.length + 3 >= length && cardStack.isEmpty() || user.userCardStackHand.length >= length)
  } else this

  def checkCardValue(user: User, firstElement: Card, topTableElement: Card): CardStack = if (valid) firstElement.rank.value match {
    case 10 => copy(cardStack, tableStack, false, valid)
    case 2 => copy(cardStack, tableStack, false, valid)
    case 3 => copy(cardStack, tableStack, reverse, valid)
    case 7 => {
      if (isValidPlay(7, topTableElement)) copy(cardStack, tableStack, true, valid)
      else copy(cardStack, tableStack, false, valid)
    }
    case _ => copy(cardStack, tableStack, reverse, isValidPlay(firstElement.rank.value, topTableElement))
  } else this

  private def isValidPlay(i: Int, card: Card): Boolean = reverse match {
    case true => i <= getLastCountingElement(card)
    case false => i >= getLastCountingElement(card)
  }

  private def getLastCountingElement(card: Card): Int = card.rank.value == 3 match {
    case true => {
      var i: Int = 3
      tableStack.cardStack.foreach(c => if (c.rank.value != 3) i = c.rank.value)
      i
    }
    case false => card.rank.value
  }

  def setValid(b: Boolean): CardStack = copy(cardStack, tableStack, reverse, b)

  def pullFromTopCardStack(): (CardStack, Card) = {
    val tuple = cardStack.pullFromTop
    (copy(tuple._2, tableStack, reverse, valid), tuple._1)
  }

  def playCard(list: List[Int], user: User): (CardStack, User) = if (valid) {
    var us: User = user
    var list2: List[Card] = List()

    if (user.getCard(list(0)).rank.value == 10 || list.length == 4) {
      list.foreach(i => us = removeCard(user.getCard(i), us))
      (copy(cardStack, tableStack.delete(), false, valid), us)
    } else {
      list.foreach(i => {
        list2 = user.getCard(i) :: list2
        us = removeCard(user.getCard(i), us)
      })
      (addToTopTableStack(list2), us)
    }
  } else (this, user)

  def addToTopTableStack(cardlist: List[Card]): CardStack = copy(cardStack, tableStack.addToTop(cardlist), reverse, valid)

  def removeCard(card: Card, user: User): User = {
    var us = user
    user.emptyHand() match {
      case true => us = user.removeTable(card)
      case false => us = user.removeHand(card)
    }
    us
  }

  def getTopTableElement(): Card = tableStack.topElement()

  private def removeCardsFromStack(user: User): (CardStack, User) = if (!valid) {
    var u: User = user.addHand(tableStack.cardStack)
    (copy(cardStack, tableStack.delete(), false, valid), u)
  } else (this, user)

}
