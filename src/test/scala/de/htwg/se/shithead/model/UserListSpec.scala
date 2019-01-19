package de.htwg.se.shithead.model

import de.htwg.se.shithead.model.userListComponent.baseImp.UserList
import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
  "A UserList" when {
    val userList = new UserList()
    val notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
    "constructed " should {

      "without parameter be empty" in {
        userList.userList should be(List())
        userList.currentUser should be(new User("",List(),List()))
      }
      "with parameter be" in {
        notEmpty.userList should be (List(new User("Hans", List(),List()),new User("Peter", List(),List())))
        notEmpty.currentUser should be (new User("Hans", List(),List()))
      }
    }

    "add user " should {
      notEmpty.addUser("Hans")
      "user be added" in {
        userList.userList.isEmpty should be(false)
      }
    }

    "remove user" should {
      notEmpty.removeUser("Hans")
      "be" in {
        userList.userListLength() should be(1)
      }
    }
  }
}