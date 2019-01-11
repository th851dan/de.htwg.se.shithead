package de.htwg.se.shithead.model

object UserList {
    var userList: List[User] = List()
    var currentUser: User = _
    var winners: List[User] = List()

    def userListLength() = userList.length

    private def isValid(name: String) = userList.exists(_.name == name)

    def getRank():Int = winners.length

    def addUser(name: String):Boolean = {
        if (!isValid(name)) {
            userList = new User(name) :: userList
            true
        } else false
    }

    def removeUser(name: String):Boolean = {
        if (isValid(name)) {
            userList = userList.filter(_.name != name)
            true
        } else false
    }

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
        for(_ <- 1 to 3) { 
            val card = CardStack.pullFromTop
            card.visibility = true
            CardStack.tableStack.addToTop(card)
        }
        currentUser = userList(0)
    }

    def getNextUser():User = {
        var i : Int = 0
        for(u <- userList) {
            if (u.equals(currentUser)) {
                i = i + 1
                if(i < userList.length) {
                    currentUser = userList(i)
                    i = 10
                } else if (i == userList.length) currentUser = userList(0)
            }
            i = i + 1
        }
        currentUser
    }

    def switchCards(c1:Int, c2:Int): String = {        
        if(c1 > 2 | c1 < 0 | c2 > 2 | c2 < 0) "Failed: Wrong Parameter\n"
        else {
            val cardH = this.currentUser.userCardStackHand(c1)
            val cardT = this.currentUser.userCardStackTable(c2)
            this.currentUser.removeHand(cardH)
            this.currentUser.removeTable(cardT)
            this.currentUser.addHand(cardT)
            this.currentUser.addTable(cardH)
            "Success\n"
        }     
    }
}