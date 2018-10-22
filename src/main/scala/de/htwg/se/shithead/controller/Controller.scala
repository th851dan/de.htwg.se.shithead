package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.Shithead
import de.htwg.se.shithead.model.CardStack
import de.htwg.se.shithead.model.Card

object Controller {
    //zustand 

    var zustand: Int = 0

    def eval(line : String) {
        var line2 = line.toLowerCase()
        if(matches(line2)) {
            line replaceFirst("^ *", "")
            val splitted = line2 split("\\s+")
            splitted(0) match {
                case "y" => println(answerYes())
                case "n" => println(answerNo())
                case "start" => println(startGame())
                case "add" => println(newUser(splitted(2)))
                case "play" => println(playCard(splitted(1) toInt))
                case "switch" => println(switchCards(splitted(1) toInt, splitted(2) toInt))
                case "remove" => println(removeUser(splitted(2)))
                case "q" => println("Adios Amigos\n")

            }
        } else {
            println("wrong syntax")
        }

    }

    def matches(line : String): Boolean = line.matches("((\\s)*y(\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
        "((\\s)*start(\\s)*)|((\\s)*(switch)(\\s)+[123](\\s)+[123](\\s)*)|" +
        "((\\s)*play(\\s)+(\\d)+(\\s)*)|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)")

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
                    "It exists  user with the same name\n"
            } else {
                "Too many users (delete user with: remove name)\n"
            }
        } else {
            "Opeartion not available\n"
        }
    }

    def startGame() {
        if (zustand == 0) {
            if (UserList.userList.size < 2) {
                "You need at least 2 players\n"
            } else {
                zustand = 1
                "Game starts: \n"
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
            for (i <- 1 to 6) {
                val card = Shithead.stack.pullFromTop._1
                card.visibility = true
                u.addHand(card)
            }
            for (i <- 1 to 3) u.addTable(Shithead.stack.pullFromTop()._1)
            for (i <- 1 to 3) {
                val card = Shithead.stack pullFromTop()._1
                card.visibility = true
                u.addTable(card)
            }
            
        }
    }

}