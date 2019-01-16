package de.htwg.se.shithead.model

object UserList {
  var userList: List[User] = List()
  var currentUser: User = _
  var fullUserListLength = 0

  def userListLength():Int = userList.length

  def getRank(): Int = fullUserListLength - userListLength()

  def addUser(name: String): Boolean = isValid(name) match {
    case false => {
      userList = userList ::: List(new User(name,List(),List()))
      fullUserListLength = fullUserListLength + 1
      true
    }
    case true => false
  }

  private def isValid(name: String):Boolean = userList.exists(_.name == name)

  def removeUser(name: String): Boolean = isValid(name) match {
    case true => {
      userList = userList.filter(_.name != name)
      true
    }
    case false => false
  }

  private def iniCards(user:User): Unit = {
    var u:User = user
    var card = CardStack.pullFromTopCardStack()
    card = card.setVisibility(true)
    u = user.addTable(card)
    u = u.addTable(CardStack.pullFromTopCardStack())
    card = CardStack.pullFromTopCardStack()
    card = card.setVisibility(true)
    u = u.addHand(card)
    userList = updateList(u)
  }

  def initialize(): Unit = {
    for(i <- 0 to userListLength() - 1; j <- 1 to 3) iniCards(userList(i))
    currentUser = userList(0)
  }

  def setNextUser(): Unit =
    for(List(left, right) <- userList.sliding(2)) {
    if(left.NAME.equals(currentUser.NAME)) currentUser = right
    else if(currentUser.NAME.equals(userList(userListLength()-1).NAME)) currentUser = userList(0)
  }

  def updateList(user:User): List[User] = {
    var list:List[User] = List()
    userList.reverse.foreach(u => if(u.NAME == user.NAME) list = user :: list else list = u :: list)
    list
  }

  def switchCards(c1: Int, c2: Int): String = c1 > 2 || c1 < 0 || c2 > 2 || c2 < 0 match {
    case true =>"Failed: Wrong Parameter\n"
    case false => {
      val cardH = currentUser.userCardStackHand(c1)
      val cardT = currentUser.userCardStackTable(c2)
      currentUser = currentUser.removeHand(cardH)
      currentUser = currentUser.removeTable(cardT)
      currentUser = currentUser.addHand (cardT)
      currentUser = currentUser.addTable(cardH)
      userList = updateList(currentUser)
      "Success\n"
    }
  }
}