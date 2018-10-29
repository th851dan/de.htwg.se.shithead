package de.htwg.se.shithead.model

object UserList {
    var userList: List[User] = List()
    
    def userListLength() = userList.length

    def addUser(name: String):Boolean = {
        if (!isValid(name)) {
            userList = new User(name) :: userList
            true
        } else {
            false
        }
    }

    def removeUser(name: String):Boolean = {
        if (isValid(name)) {
            userList = userList.filter(_.name != name)
            true
        } else {
            false
        }
    }

    private def isValid(name: String) = userList.exists(_.name == name)
}