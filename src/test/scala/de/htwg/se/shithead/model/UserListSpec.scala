package de.htwg.se.shithead.model

import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
    "An UserList" when {
        "created" should {
            val userList = UserList
            "have new userList empty" in {
                userList.userList.isEmpty should be(true)
            }
            "have currentUser None" in {
                userList.currentUser should be(null)
            }
            "have winner list empty" in {
                userList.winners.isEmpty should be(true)
            }
        }
    }
    "An UserList" when {
        "userListLength is called" should {
            val userList = UserList
            userList.addUser("test")
            "return lenght of userList" in {
                userList.userListLength should be(1)
            }
        }
    }
    "An UserList" when {
        "isValid is called" should {
            val userList = UserList
            userList.addUser("test")
            "return true if user is in userList" in {
                userList.isValid("test") should be(true)
            }
            "return false if user is not in userList" in {
                userList.isValid("tes") should be(false)
            }
        }
    }
    "An UserList" when {
        "addUser is called" should {
            val userList = UserList
            "return true if possible" in {
                userList.addUser("test") should be(true)
            }
            "return false if not possible" in {
                userList.addUser("test") should be(false)
            }
        }
    }
    "An UserList" when {
        "removeUser is called" should {
            val userList = UserList
            "return false if not possible" in {
                userList.removeUser("test") should be(false)
            }
            userList.addUser("test")
            "return true if possible" in {
                userList.removeUser("test") should be(true)
            }
        }
    }
/*
    "An UserList" when {
        "initialize is called" should {

        }
    }
*/
    "An UserList" when {
        "getNextUser is called" should {
            val userList = UserList
            userList.addUser("test1")
            userList.addUser("test2")
            userList.initialize
            "return next user" in {
                userList.getNextUser should be(userList.userList(1))
            }
            "return first user if currentUser is last" in {
                userList.getNextUser should be(userList.userList(0))
            }
        }
    }
}