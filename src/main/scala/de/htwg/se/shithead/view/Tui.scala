package de.htwg.se.shithead.view

import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.User
import de.htwg.se.shithead.model.Card

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
}