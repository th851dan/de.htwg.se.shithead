package de.htwg.se.shithead.model

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

    def getNextUser():User = {
        var i : Int = 0
        for(u <- userList) {
            if (u.equals(currentUser)) {
                i = i + 1
                if(userList(i) != null) { // Hier noch weg finden für nicht null
                    currentUser = userList(i)
                    0
                } else {
                    currentUser = userList(0)
                }
            }
            i = i + 1
        }
        currentUser
    }

    def switchCards(c1:Int, c2:Int): String = {        
        if(c1 > 2 | c1 < 0 | c2 > 2 | c2 < 0)
            "Failed: Wrong Parameter\n"
        else {
            val cardH = this.currentUser.userCardStackHand(c1)
            this.currentUser.userCardStackHand.updated(c1,this.currentUser.userCardStackTable(c2))
            this.currentUser.userCardStackTable.updated(c2, cardH)    
            "success\n"
        }     
    }
}