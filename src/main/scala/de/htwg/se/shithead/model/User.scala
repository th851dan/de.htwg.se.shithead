package de.htwg.se.shithead.model

case class User(name: String, stackHand: List[Card], stackTable: List[Card]) {
  val NAME: String = name
  val userCardStackHand: List[Card] = stackHand
  val userCardStackTable: List[Card] = stackTable

  def addHand(list: List[Card]): User = copy(NAME, list ::: userCardStackHand, userCardStackTable)

  def addHand(card: Card): User = copy(NAME, card :: userCardStackHand, userCardStackTable)

  def removeHand(card: Card): User = copy(NAME, remove(card, userCardStackHand), userCardStackTable)

  def addTable(card: Card): User = copy(NAME, userCardStackHand, card :: userCardStackTable)

  def removeTable(card: Card): User = copy(NAME, userCardStackHand, remove(card, userCardStackTable))

  private def remove(card: Card, list: List[Card]): List[Card] = list diff List(card)

  override def equals(obj: Any): Boolean = obj.isInstanceOf[User] match {
    case true => if (obj.asInstanceOf[User].NAME.equals(this.NAME)) true else false
    case false => false
  }

  def getCard(id: Int): Card = emptyHand() match {
    case true => {
      if (userCardStackTable.length > 3) userCardStackTable(id + 3)
      else userCardStackTable(id)
    }
    case false => userCardStackHand(id)
  }

  def emptyHand(): Boolean = userCardStackHand.isEmpty
}
