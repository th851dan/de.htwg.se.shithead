package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.Card
import de.htwg.se.shithead.model.State

object Controller {
    //status
    var status: Int = 0

    def buildAll(b: Boolean):String = { 
        val sb = new StringBuilder()
        for(u <- UserList userList) {
            sb.append(build(u ,b))
        }
        sb.toString
    }

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

    def getUserListLength():Int = UserList.userListLength()

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

    def compareToStartUser():Boolean = getCurrentUser.equals(UserList.userList(0))

    def getTurn():String = getCurrentUserName + " It's your turn: "

    def playCard(list:List[Int]): Boolean = {
        val user: User = getCurrentUser
        val size: Int = getCurrentUserCardStackLength
        var b:Boolean = true

        if(list.length > size && user.isHand() || list.length <= size) {
            val firstElement = user.getCard(list(0))
            var topCard = CardStack.getTopValue()
            if(topCard.toString().equals("?")) b = true 
            else if(topCard.rank.value >= firstElement.rank.value) b = false
            for(i <- list)
                if(!user.getCard(i).equals(firstElement)) b = false
        } else  b = false
        b
    }

    def getCurrentUserCardStackLength() = getCurrentUser.size()
}