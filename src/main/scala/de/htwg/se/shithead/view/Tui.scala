package de.htwg.se.shithead.view

import de.htwg.se.shithead.model.UserList
import de.htwg.se.shithead.model.User

object Tui {
    var user: User = null

    def show(u: User) = print(build(u, UserList.userList).toString)

    private def build(u: User, userList: List[User]) = {
        var user = u
        var stringBuilder: StringBuilder = new StringBuilder()
        for(u <- userList) stringBuilder.append(u.name).append(":  ").append(u.userCardStackTable).append("\n")
        stringBuilder.append("\n")
        stringBuilder.append(user.name).append(":  ").append(user.userCardStackHand).append("\n")
    }

    def update() = show(user)
}