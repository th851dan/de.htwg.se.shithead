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
}