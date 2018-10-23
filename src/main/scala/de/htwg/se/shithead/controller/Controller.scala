package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.Shithead
import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.Card
import de.htwg.se.shithead.view.Tui

object Controller {
    //zustand 

    var zustand: Int = 0

    def answerYes(): String = {
        if (zustand == 3) {
            "hallo"
        } else {
            "Opeartion not available"
        }
    }

    def answerNo(): String = {
        if (zustand == 2) {
            "bla"
        } else {
            "Opeartion not available"
        } 
    }
    
    def newUser(name : String): String = {
        if (zustand == 0) {
            if (UserList.userList.size  < 6) {
                if (UserList addUser(name))
                    "New user added: " + name + "\n"
                else
                    "There is someone with the same name\n"
            } else {
                "Too many users (delete user with: remove name)\n"
            }
        } else {
            "Opeartion not available\n"
        }
    }

    def startGame(){
        if (zustand == 0) {
            if (UserList.userList.size < 2) {
                println("You need at least 2 players\n")
            } else {
                zustand = 1
                println("Game starts: \n")
                start
            }

        } else {
            "Opeartion not available\n"
        }
    }

    def switchCards(id1 : Int, id2 : Int): String = {
        if (zustand == 3) {
            "hallo"
        } else {
            "Opeartion not available\n"
        }
    }

    def playCard(id : Int): String = {
        if (zustand == 4) {
            "hallo"
        } else {
            "Opeartion not available\n"
        }
    }

    def removeUser(name : String): String = {
        var bool: Boolean = false
        if (zustand == 0) {
            if (UserList.userList.size == 0)
                "No users available\n"
            else if (UserList removeUser(name))
                "Success\n"
            else
                "This user does not exist\n"
        } else    "Opeartion not available\n"
    }

    def start() = {

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
        Tui showAll(true)
    }
}