package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.UserList


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
                case "new" => println(newUser(splitted(2)))
                case "play" => println(playCard(splitted(1) toInt))
                case "switch" => println(switchCards(splitted(1) toInt, splitted(2) toInt))
                case "remove" => println(removeUser(splitted(1)))

            }
        } else {
            println("Dieses Kommando gibt es nicht.")
        }

    }

    def matches(line : String): Boolean = line.matches("((\\s)*y(\\s)*)|((\\s)*n(\\s)*)|((\\s)*(new)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
        "((\\s)*start(\\s)*)|((\\s)*(switch)(\\s)+[123](\\s)+[123](\\s)*)|" +
        "((\\s)*play(\\s)+(\\d)+(\\s)*)|((\\s)*remove(\\s)+(\\w){2,20}")

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
                    "new User " + name + "\n"
                else
                    "It exists  user with the same name"
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
                
                "Es geht los: \n"
            }

        } else {
            "Opeartion not available"
        }
    }

    def switchCards(id1 : Int, id2 : Int): String = {
        if (zustand == 3) {
            "hallo"
        } else {
            "Opeartion not available"
        }
    }

    def playCard(id : Int): String = {
        if (zustand == 4) {
            "hallo"
        } else {
            "Opeartion not available"
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

}