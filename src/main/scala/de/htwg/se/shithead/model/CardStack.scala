package de.htwg.se.shithead.model

import scala.util.Random

object CardStack {

  val suites = Set(Spade, Heart, Club, Diamond)
  val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val cards: List[Card] = for (r <- ranks; s <- suites) yield Card(r, s, false)
  var cardStack = Stack(cards, true)
  var tableStack = Stack(List(), false)
  var reverse: Boolean = false
  var valid: Boolean = true

  def addToTopCardStack(card: Card): Unit = cardStack = cardStack.addToTop(card)

  def addToTopCardStack(cardsToAdd: List[Card]): Unit = cardStack = cardStack.addToTop(cardsToAdd)

  def addToTopTableStack(card: Card): Unit = tableStack = tableStack.addToTop(card)

  def shuffleCardStack(): Unit = cardStack = cardStack.shuffle

  def pullFromTopCardStack(): Card = {
    val tuple = cardStack.pullFromTop
    cardStack = tuple._2
    tuple._1
  }

  private def checkEquality(list: List[Int], user: User, card: Card): Unit = list.foreach(i => if (user.getCard(i).rank.value != card.rank.value) valid = false)

  private def checkListLength(user: User, length: Int): Unit = if (valid) user.userCardStackHand.length == 0 match {
    case true => valid = user.userCardStackTable.length >= length
    case false => valid = user.userCardStackHand.length + 3 >= length && cardStack.isEmpty() || user.userCardStackHand.length >= length
  }

  private def getLastCountingElement(card:Card): Int = card.rank.value == 3 match {
    case true => {
      var i: Int = 3
      tableStack.cardStack.reverse.foreach(c => if (c.rank.value != i) i = c.rank.value)
      i
    }
    case false => card.rank.value
  }

  def isValidPlay(i: Int, card:Card): Boolean = reverse match {
    case true => i <= getLastCountingElement(card)
    case false => i >= getLastCountingElement(card)
  }

  private def checkCardValue(user: User, firstElement: Card, topTableElement: Card): Unit = if(valid) topTableElement.rank.value match {
    case 10 => reverse = false
    case 2 => reverse = false
    case 3 =>
    case 7 => {
      if(isValidPlay(7,topTableElement))
        reverse = true
      else valid = false
    }
    case _ => valid = isValidPlay(firstElement.rank.value, topTableElement)
  }

  private def addCardsToHand(user: User): Unit = if(!valid) {
    var u:User = user
    u = u.addHand(tableStack.cardStack)
    UserList.updateList(u)
    tableStack.delete(false)
  }

  private def setNextUser(value:Int): Unit = value match {
    case 10  => if(!valid) UserList.setNextUser()
    case 8 => if(valid) for(_ <- 0 to 1) UserList.setNextUser() else UserList.setNextUser()
    case _ => UserList.setNextUser()
  }

  def playCard(list: List[Int]): Boolean = {
    val user: User = UserList.currentUser
    val firstElement = user.getCard(list(0))
    valid = true
    checkEquality(list, user, firstElement)
    checkListLength(user, list.length)
    if (tableStack.cardStack.length != 0) checkCardValue(user, firstElement, getTopTableElement())
    addCardsToHand(user)
    playCard(list, user)
    setNextUser(firstElement.rank.value)
    valid
}

  def remove(card: Card,user: User): Unit = {
      var us = user
      user.userCardStackHand.length match {
        case 0 => us = user.removeTable(card)
        case _ => us = user.removeHand(card)
      }
      UserList.userList = UserList.updateList(us)
  }

  def playCard(list:List[Int], user: User): Unit =if(valid) list.foreach(i => {
    addToTopTableStack(user.getCard(i))
    remove(user.getCard(i), user)
  })

  def getTopTableElement(): Card = tableStack.topElement()

  case class Stack(cards: List[Card], isValid: Boolean) {

    val cardStack: List[Card] = if (isValidStack(cards) && isValid || !isValid) cards else throw new RuntimeException("Stack is invalid")

    def shuffle(): Stack = new Stack(Random.shuffle(cardStack), isValid)

    def pullFromTop(): (Card, Stack) = (cardStack.head, new Stack(cardStack.tail, isValid))

    def addToTop(card: Card): Stack = copy(card :: cardStack, isValid)

    def addToTop(cardsToAdd: List[Card]): Stack = copy(cardsToAdd ::: cardStack, isValid)

    def topElement(): Card = cardStack(cardStack.length - 1)

    def isEmpty(): Boolean = cardStack.length == 0

    def delete(boolean: Boolean): Stack = copy(List(), boolean)

    private def isValidStack(cards: List[Card]): Boolean = cards.size <= 52 && cards.distinct.size == cards.size
  }
}