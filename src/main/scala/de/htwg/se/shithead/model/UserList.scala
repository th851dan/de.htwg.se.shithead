package de.htwg.se.shithead.model

object UserList {
    var userList: List[User] = List()

    def addUser(name: String) = {
        if (addActionIsValid(name)) {
            userList = new User(name) :: userList
            true
        } else {
            false
        }
    }

    def removeUser(name: String) = {
        if (removeActionIsValid(name)) {
            userList = userList.filter(_.name == name)
            true
        } else {
            false
        }
    }

    private def removeActionIsValid(name: String) = userList.exists(_.name == name)

    private def addActionIsValid(name: String) = userList.exists(_.name != name)
}