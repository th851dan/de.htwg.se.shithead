package de.htwg.se.shithead.controller

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

            }
        } else {
            println("Dieses Kommando gibt es nicht.")
        }

    }

    def matches(line : String): Boolean = line.matches("((\\s)*y(\\s)*)|((\\s)*n(\\s)*)|((\\s)*(new)(\\s)+user(\\s)+(\\w){2,20}(\\s)*)|" +
        "((\\s)*start(\\s)*)|((\\s)*(switch)(\\s)+[123](\\s)+[123](\\s)*)|" +
        "((\\s)*play(\\s)+(\\d)+(\\s)*)")

    def answerYes(): String = {
        if (zustand == 3) {
            "hallo"
        } else {
            "Dieses Kommando ist nicht möglich"
        }
    }

    def answerNo(): String = {
        if (zustand == 2) {
            "bla"
        } else {
            "Dieses Kommando ist nicht möglich"
        } 
    }
    
    def newUser(name : String): String = {
        if (zustand == 0) {
            "hallo"    
        } else {
            "Dieses Kommando ist nicht möglich."
        }
    }

    def startGame() {
        if (zustand == 1) {
            "hallo"
        } else {
            "Dieses Kommando ist nicht möglich"
        }
    }

    def switchCards(id1 : Int, id2 : Int): String = {
        if (zustand == 3) {
            "hallo"
        } else {
            "Dieses Kommando ist nicht möglich"
        }
    }

    def playCard(id : Int): String = {
        if (zustand == 4) {
            "hallo"
        } else {
            "Dieses Kommando ist nicht möglich"
        }
    }

}