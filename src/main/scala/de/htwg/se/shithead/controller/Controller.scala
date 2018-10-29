package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.Card

object Controller {
    //status
    var status: Int = 0
    var nextUser: User = _

    def begin() = {
        for (u <- UserList.userList) {
            var i = 0
            for (i <- 1 to 3) {
                var card = CardStack.pullFromTop
                card.visibility = true
                u.addHand(card)
            }
            for (i <- 1 to 3) u.addTable(CardStack.pullFromTop)
            for (i <- 1 to 3) {
                var card = CardStack.pullFromTop
                card.visibility = true
                u.addTable(card)
            }
        }
    }

    def build(u: User,b: Boolean):String = {
        var user = u
        var sb: StringBuilder = new StringBuilder()
        var i:Int = 0
        if (u.userCardStackHand.size != 0) {
            sb.append(u.name + ": \nCards on your hand: \n" )
            for(card <- u.userCardStackHand) {
                if(i % 3 == 0 && i != 0) sb.append("\n")
                sb.append(card + " ")
                i+=1;
            }
        }
        if(b) {
            sb.append("\n\nCards on the table: \n")
            i = 0
            for (card <- u.userCardStackTable) {
                if (!card.visibility) i+=1;
                else sb.append(card + " ")
            }   
            sb.append("\nand " + i + " face-down cards\n\n")
        } else {
            sb.append("\n\n")
        }
        sb toString()
    }

    def getUserListLength():Int = UserList.userListLength() + 1

    def buildAll(b: Boolean):String = { 
        var sb = new StringBuilder()
        for(u <- UserList userList) {
            sb.append(build(u ,true))
        }
        sb.toString
    }

    def build(b:Boolean): String = build(nextUser,b)

    def remove(name: String):Boolean = UserList.removeUser(name)

    def add(name:String):Boolean = UserList.addUser(name)

    def setStatus(status: Int) = this status = status

    def getStatus() = this status    



}