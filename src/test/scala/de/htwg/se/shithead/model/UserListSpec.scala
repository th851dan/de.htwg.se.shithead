package de.htwg.se.shithead.model

import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
    "An UserList" when {
        val userList = UserList
        userList.addUser("test1")
        userList.initialize
        "userListLength is called" should {
            "return lenght of userList" in {
                userList.userListLength should be(userList.userList.length)
            }
        }
        "getRank is called" should {
            "return rank" in {
                userList.getRank should be(userList.fullUserListLength - userList.userListLength)
            }
        }
        "addUser is called" should {
            "return true if possible" in {
                userList.addUser("test2") should be(true)
            }
            "return false if not possible" in {
                userList.addUser("test2") should be(false)
            }
        }
        userList.addUser("test")
        "removeUser is called" should {
            "return true if possible" in {
                userList.removeUser("test") should be(true)
            }
            "return false if not possible" in {
                userList.removeUser("test") should be(false)
            }
            userList.addUser("test")
            val user = userList.userList.filter(_.name == "test")
            userList.removeUser("test")
            "remove user from list" in {
                userList.userList.contains(user) should be(false)
            }
        }
        userList.initialize
        "initialize is called" should {
            "currentUser should be updated to first entry in userList" in {
                userList.currentUser should be(userList.userList(0))
            }
        }
        
    }
}
     /*
    "An UserList" when {
    }
    "An UserList" when {
    }
    "An UserList" when {
        
    }
    "An UserList" when {
        "setNextUser is called" should {
            val userList = UserList
            userList.addUser("test1")
            userList.addUser("test2")
            userList.initialize
            "currentUser should be second element of userList" in {
                userList.setNextUser
                userList.currentUser should be(userList.userList(1))
            }
            "currentUser should be first element of userList" in {
                userList.setNextUser
                userList.currentUser should be(userList.userList(0))
            }
        }
    }

    "An UserList" when {
        "updateList is called" should {
            val userList = UserList
            userList.addUser("test1")
            userList.initialize

        }
    }

}
*/