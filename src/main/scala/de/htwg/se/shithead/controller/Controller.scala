package de.htwg.se.shithead.controller

import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.Shithead
import de.htwg.se.shithead.


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
                case "remove" => println(removeUser())

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
            if (Shithead userlist length  < 6) {
                val bla = new User(name) 
                bla :: Shithead.userlist
                "Benutzer wurde angefügt"
            } else {
                "Es sind bereits zuviele Benutzer (löschen mit: remove name)"
            }
        } else {
            "Opeartion not available"
        }
    }

    def startGame() {
        if (zustand == 1) {
            "hallo"
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
            if (Shithead userlist length != 0) {
                Shithead userlist foreach {
                    if (_ equals name) {
                        bool = true

                    }
                    if(bool) "User removed\n"
                    else "Exsistiert nicht\n"
                }
            } else {
                "No exsisting user\n"
            }
        } else {
            "Opeartion not available\n"
        }
    }

}