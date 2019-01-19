package de.htwg.se.shithead.model.cardStackComponent.mockImp

import de.htwg.se.shithead.model._
import de.htwg.se.shithead.model.stackComponent.mockImp.Stack

case class CardStack(cardStack: Stack, tableStack: Stack, reverse:Boolean, valid: Boolean) extends CardStackInterface{

  def deleteTableStack(): CardStack = this

  def addToTopCardStack(card: Card): CardStack = this

  def addToTopCardStack(cardlist: List[Card]): CardStack = this

  def addToTopTableStack(card: Card): CardStack = this

  def addToTopTableStack(cardlist: List[Card]): CardStack = this

  def shuffleCardStack(): CardStack = this

  def checkEquality(list: List[Int], user: User, card: Card): Boolean =  false

  def checkListLength(user: User, length: Int): CardStack = this

  def checkCardValue(user: User, firstElement: Card, topTableElement: Card): CardStack = this

  def setValid(b: Boolean): CardStack = this

  def pullFromTopCardStack(): (CardStack, Card) = (this, Card(Two,Spade,false))


  def playCard(list: List[Int], user: User): (CardStack, User) = (this, User("",List(),List()))

  def removeCard(card: Card, user: User): User = User("",List(),List())

  def getTopTableElement(): Card = Card(Two,Spade,false)

}
