package de.htwg.se.shithead.model

case class User(name: String, stackHand:List[Card], stackTable:List[Card]) {
  val NAME: String = name
  val userCardStackHand: List[Card] = stackHand
  val userCardStackTable: List[Card] = stackTable

  def addHand(list: List[Card]): User = copy(NAME, list ::: userCardStackHand, userCardStackTable)

  def addHand(card: Card):User = copy(NAME, card :: userCardStackHand, userCardStackTable)

  def removeHand(card: Card): User = copy(NAME, remove(card,userCardStackHand), userCardStackTable)

  def addTable(card: Card): User = copy(NAME, userCardStackHand, card :: userCardStackTable)

  def removeTable(card: Card):User = copy(NAME, userCardStackHand, remove(card, userCardStackTable))

  private def remove(card: Card, list:List[Card]): List[Card] = list diff List(card)

  def emptyHand(): Boolean = userCardStackHand == 0

  def getCard(id: Int): Card = userCardStackHand.length match {
    case 0 => {
      if (userCardStackTable.length > 3) userCardStackTable(id + 3)
      else userCardStackTable(id)
    }
    case _ => userCardStackHand(id)
  }
}