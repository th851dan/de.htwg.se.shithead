package de.htwg.se.shithead.model

object UserList {
    var userList: List[User] = List()

    def addUser(name: String) = {
        userList = new User(name) :: userList
    }
}