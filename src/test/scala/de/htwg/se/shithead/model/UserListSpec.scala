package de.htwg.se.shithead.model

import de.htwg.se.shithead.model.userListComponent.baseImp.UserList
import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
  "A UserList" when {
    "constructed " should {

      "without parameter be empty" in {
        val userList = new UserList()
        userList.userList should be(List())
        userList.currentUser should be(new User("",List(),List()))
      }
      "with parameter be" in {
        val notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
        notEmpty.userList should be (List(new User("Hans", List(),List()),new User("Peter", List(),List())))
        notEmpty.currentUser should be (new User("Hans", List(),List()))
      }
    }

    "add user " should {
      var userList = new UserList()
      userList = userList.addUser("Hans")
      "user be added" in {
        userList.userList.isEmpty should be(false)
      }
    }

    "remove user" should {
      var notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
      notEmpty = notEmpty.removeUser("Hans")
      "be" in {
        notEmpty.userListLength() should be(1)
      }
    }
  }
}