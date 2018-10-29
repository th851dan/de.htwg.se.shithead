package de.htwg.se.shithead.view

import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.Card
import de.htwg.se.shithead.controller.Controller

object Tui {
    var user: User = _

    def show(u: User, b: Boolean) = println(build(u,b).toString)

    def showAll(b:Boolean) =  for (user <- UserList.userList) println(build(user,b))

    private def build(u: User,b: Boolean) = {
        var user = u
        var sb: StringBuilder = new StringBuilder()
        var i:Int = 0
        sb.append(u.name + ": \nCards on your hand: \n" )
        for(card <- u.userCardStackHand) {
            if(i % 3 == 0 && i != 0) sb.append("\n")
            sb.append(card + " ")
            i+=1;
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
    }

    def update() = show(user,true)

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
                case "y" => println(Controller.answerYes())
                case "n" => println(Controller.answerNo())
                case "start" => Controller.startGame()
                case "add" => println(Controller.newUser(splitted(2)))
                case "play" => println(Controller.playCard(splitted(1) toInt))
                case "switch" => println(Controller.switchCards(splitted(1) toInt, splitted(2) toInt))
                case "remove" => println(Controller.removeUser(splitted(2)))
                case "q" => println("Adios Amigos\n")
            }
        } else {
            println("wrong syntax\n")
        }

    }

}