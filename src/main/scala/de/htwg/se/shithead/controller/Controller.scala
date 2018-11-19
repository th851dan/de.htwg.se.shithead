package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.Card
import de.htwg.se.shithead.model.State

object Controller {
    //status
    var status: Int = 0
    var startUser: User = _

    def build(u: User,b: Boolean):String = {
        var user:User = u
        val sb : StringBuilder = new StringBuilder()
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
        val sb = new StringBuilder()
        for(u <- UserList userList) {
            sb.append(build(u ,true))
        }
        sb.toString
    }

    def begin() = UserList.initialize()

    def build(b:Boolean):String = build(getCurrentUser(),b)

    def remove(name: String):Boolean = UserList.removeUser(name)

    def add(name:String):Boolean = UserList.addUser(name)
    
    def setState(status: Int) = this status = status

    def getState() = this status

    def getCurrentUser():User = UserList.currentUser
    
    def getCurrentUserName():String = UserList.currentUser.NAME

    def setNextUser():User = UserList.getNextUser

    def changeCards(card1:Int, card2:Int):String = UserList.switchCards(card1 - 1,card2 - 1)

    def setStartUser() = startUser = UserList.userList(0)

    def compareToStartUser():Boolean = setNextUser.equals(startUser)
}