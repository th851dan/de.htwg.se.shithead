package de.htwg.se.shithead.model
import de.htwg.se.shithead.model.CardStack

object UserList {
    var userList: List[User] = List()
    var currentUser: User = _

    def userListLength() = userList.length

    def addUser(name: String):Boolean = {
        if (!isValid(name)) {
            userList = new User(name) :: userList
            true
        } else {
            false
        }
    }

    def removeUser(name: String):Boolean = {
        if (isValid(name)) {
            userList = userList.filter(_.name != name)
            true
        } else {
            false
        }
    }

    private def isValid(name: String) = userList.exists(_.name == name)

    def initialize() = {
        for (u <- userList) {
            for (_ <- 1 to 3) {
                val card = CardStack.pullFromTop
                card.visibility = true
                u.addHand(card)
            }
            for (_ <- 1 to 3) u.addTable(CardStack.pullFromTop)
            for (_ <- 1 to 3) {
                val card = CardStack.pullFromTop
                card.visibility = true
                u.addTable(card)
            }
        }
        currentUser = userList(0)
    }

    def getNextUser() = {
        var i : Int = 0
        for(u <- userList) {
            if (u.equals(currentUser)) {
                i = i + 1
                if(userList(i) != null) {
                    currentUser = userList(i)
                    0
                } else {
                    currentUser = userList(0)
                }
            }
            i = i + 1
        }
    }
}