package de.htwg.se.shithead.model.baseImp

import de.htwg.se.shithead.model._

import scala.util.Random

case class Stack(cards: List[Card], isValid: Boolean) extends StackInterface{

  val cardStack: List[Card] = if (isValidStack(cards) && isValid || !isValid) cards else throw new RuntimeException("Stack is invalid")

  def shuffle(): Stack = new Stack(Random.shuffle(cardStack), isValid)

  def pullFromTop(): (Card, Stack) = (cardStack.head, new Stack(cardStack.tail, isValid))

  def addToTop(card: Card): Stack = copy(card :: cardStack, isValid)

  def addToTop(cardsToAdd: List[Card]): Stack = copy(cardsToAdd ::: cardStack, isValid)

  def topElement(): Card = cardStack(0)

  def isEmpty(): Boolean = cardStack.length == 0

  def delete(): Stack = copy(List(), isValid)

  private def isValidStack(cards: List[Card]): Boolean = cards.size <= 52 && cards.distinct.size == cards.size
}
