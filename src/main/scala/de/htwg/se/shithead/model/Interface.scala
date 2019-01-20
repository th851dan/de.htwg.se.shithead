package de.htwg.se.shithead.model

trait UserListInterface {
  val userList: List[User]
  val currentUser: User

  def userListLength(): Int

  def addUser(name: String): UserListInterface

  def setCurrentUser(i: Int): UserListInterface

  def removeUser(name: String): UserListInterface

  def updateList(user: User): UserListInterface

  def setNextUser(): UserListInterface

  def getTrueTableCard(user: User, int: Int): Card

  def switchCards(c1: Int, c2: Int): UserListInterface
}

trait CardStackInterface {
  val cardStack: StackInterface
  val tableStack: StackInterface
  val reverse: Boolean
  val valid: Boolean

  def deleteTableStack(): CardStackInterface

  def addToTopCardStack(card: Card): CardStackInterface

  def addToTopCardStack(cardlist: List[Card]): CardStackInterface

  def addToTopTableStack(card: Card): CardStackInterface

  def addToTopTableStack(cardlist: List[Card]): CardStackInterface

  def shuffleCardStack(): CardStackInterface

  def checkEquality(list: List[Int], user: User, card: Card): Boolean

  def checkListLength(user: User, length: Int): CardStackInterface

  def checkCardValue(user: User, firstElement: Card, topTableElement: Card): CardStackInterface

  def setValid(b: Boolean): CardStackInterface

  def pullFromTopCardStack(): (CardStackInterface, Card)

  def playCard(list: List[Int], user: User): (CardStackInterface, User)

  def removeCard(card: Card, user: User): User

  def getTopTableElement(): Card

}

trait StackInterface {
  val cards: List[Card]
  val isValid: Boolean
  val cardStack: List[Card]

  def shuffle(): StackInterface

  def pullFromTop(): (Card, StackInterface)

  def addToTop(card: Card): StackInterface

  def addToTop(cardsToAdd: List[Card]): StackInterface

  def topElement(): Card

  def isEmpty(): Boolean

  def delete(): StackInterface

}

trait FileIOInterface {

  def load(): (CardStackInterface, UserListInterface)

  def save(cardStack: CardStackInterface, userList: UserListInterface): Unit
}