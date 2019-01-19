package de.htwg.se.shithead.model.stackComponent.mockImp

import de.htwg.se.shithead.model.{Card, Spade, StackInterface, Two}

case class Stack(cards: List[Card], isValid: Boolean) extends StackInterface{

  val cardStack: List[Card] = List()

  def shuffle(): Stack = this

  def pullFromTop(): (Card, Stack) = (new Card(Two,Spade,false),this)

  def addToTop(card: Card): Stack = this

  def addToTop(cardsToAdd: List[Card]): Stack = this

  def topElement(): Card = new Card(Two,Spade,false)

  def isEmpty(): Boolean = false

  def delete(): Stack = this
}
