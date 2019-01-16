package de.htwg.se.shithead.model

import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
    "An UserList" when {
        val userList = UserList
        userList.addUser("test1")
        userList.initialize
        "initialize is called" should {
            "currentUser should be updated to first entry in userList" in {
                userList.currentUser should be(userList.userList(0))
            }
        }
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
        userList.addUser("hooligan")
        "removeUser is called" should {
            "return true if possible" in {
                userList.removeUser("hooligan") should be(true)
            }
            "return false if not possible" in {
                userList.removeUser("hooligan") should be(false)
            }
            userList.addUser("test")
            val user = userList.userList.filter(_.name == "test")
            userList.removeUser("test")
            "remove user from list" in {
                userList.userList.contains(user) should be(false)
            }
        }
        "setNextUser is called" should {
            "update currentUser to next user" in {
                userList.setNextUser
                userList.currentUser should be(userList.userList(1))
            }
            "update currentUser to first user in userList when end of list is reachd" in {
                userList.setNextUser
                userList.currentUser should be(userList.userList(0))
            }
        }
        "switchCards is called" should {
            "switch card on table with card in hand of currentUser" in {
                val cardHand = userList.currentUser.userCardStackHand(0)
                val cardTable = userList.currentUser.userCardStackTable(0)
                userList.switchCards(0, 0)
                userList.currentUser.userCardStackHand.contains(cardTable) should be(true)
                userList.currentUser.userCardStackTable.contains(cardHand) should be(true)
            }
        }
    }
}