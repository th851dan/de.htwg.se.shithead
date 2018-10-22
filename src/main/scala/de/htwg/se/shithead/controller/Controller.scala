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

    def eval(line : String) {
        if(matches(line)) {
            line replaceFirst("^ *", "")
            val splitted = line split("\\s+")
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

    def matches(line : String): Boolean = {
        var line2 = line.toLowerCase
        line2.matches("((\\s)*y(\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
        "((\\s)*start(\\s)+game(\\s)*)|((\\s)*(switch)(\\s)+[123](\\s)+[123](\\s)*)|" +
        "((\\s)*play(\\s)+(\\d)+(\\s)*)|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)")
    }

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
        println("hans")
        for (u <- UserList.userList) {
            var i = 0
            
            for (i <- 1 to 6) {
                var card = CardStack.cardStack.pullFromTop._1
                card.visibility = true
                u.addHand(card)
                println(u.toString + " " + card)
            }
            for (i <- 1 to 3) u.addTable(CardStack.cardStack.pullFromTop._1)
            for (i <- 1 to 3) {
                var card = CardStack.cardStack.pullFromTop._1
                card.visibility = true
                u.addTable(card)
            }
        }
        var User = ()
        for(user <- UserList.userList) Tui.show(user)
    }
}