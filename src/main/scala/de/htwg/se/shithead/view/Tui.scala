package de.htwg.se.shithead.view

import de.htwg.se.shithead.controller.Controller

object Tui {

    def matches(line : String): Boolean = {
        var line2 = line.toLowerCase
        line2.matches("((\\s)*y(\\s)*)|((\\s)*n(\\s)*)|((\\s)*(add)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
        "((\\s)*start(\\s)+game(\\s)*)|((\\s)*(switch)(\\s)+[123](\\s)+[123](\\s)*)|" +
        "((\\s)*play(\\s)+(\\d)+(\\s)*)|((\\s)*remove(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|((\\s)*q(\\s)*)")
    }

    def eval(line : String) {
        if(matches(line)) {
            line replaceFirst("^ *", "")
            val splitted = line split("\\s+")
            splitted(0) = splitted(0).toLowerCase
            splitted(0) match {
                case "y" => println(answerYes())
                case "n" => println(answerNo())
                case "start" => startGame()
                case "add" => println(newUser(splitted(2)))
                case "play" => println(playCard(splitted(1) toInt))
                case "switch" => println(switchCards(splitted(1) toInt, splitted(2) toInt))
                case "remove" => println(removeUser(splitted(2)))
                case "q" => println("Adios Amigos\n")
            }
        } else {
            println("wrong syntax\n")
        }

    }

        def answerYes(): String = {
        if (Controller.getStatus() == 3) {
            "hallo"
        } else {
            "Opeartion not available"
        }
    }

    def answerNo(): String = {
        if (Controller.getStatus() == 2) {
            "bla"
        } else {
            "Opeartion not available"
        } 
    }
    
    def newUser(name : String): String = {
        if (Controller.getStatus == 0) {
            if (Controller.getUserListLength()  < 6) {
                if (Controller add(name))
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
        if (Controller.getStatus() == 0) {
            if (Controller.getUserListLength < 2) {
                println("You need at least 2 players\n")
            } else {
                Controller.setStatus(1)
                println("Game starts: \n")
                start
            }

        } else {
            "Opeartion not available\n"
        }
    }

    def switchCards(id1 : Int, id2 : Int): String = {
        if (Controller.getStatus() == 3) {
            "hallo"
        } else {
            "Opeartion not available\n"
        }
    }

    def playCard(id : Int): String = {
        if (Controller.getStatus() == 4) {
            "hallo"
        } else {
            "Opeartion not available\n"
        }
    }

    def removeUser(name : String): String = {
        var bool: Boolean = false
        if (Controller.getStatus() == 0) {
            if (Controller.getUserListLength() == 0)
                "No users available\n"
            else if (Controller.remove(name))
                "Success\n"
            else
                "This user does not exist\n"
        } else    "Opeartion not available\n"
    }

    def start() = {
        if (Controller.getStatus() == 1) {
            Controller begin()
            println("Start")
            showAll(true)
        } else if (Controller.getUserListLength() < 2) {
            println("You need at least 2 players")
        } else {
            println("You are already ingame")
        }
    }

    def show(b: Boolean) = println(Controller.build(b))

    def showAll(b:Boolean) =  println(Controller.buildAll(b))

}