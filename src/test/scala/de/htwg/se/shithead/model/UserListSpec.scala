package de.htwg.se.shithead.model

import de.htwg.se.shithead.model.userListComponent.baseImp.UserList
import org.scalatest._

class UserListSpec extends WordSpec with Matchers {
  def card:Card = new Card(Two,Spade,true)
  def card2:Card = new Card(Three, Spade, true)
  "A UserList" when {
    "constructed " should {

      "without parameter be empty" in {
        val userList = new UserList()
        userList.userList should be(List())
        userList.currentUser should be(new User("",List(),List()))
      }

      "with parameter be the list with the added users" in {
        val notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
        notEmpty.userList should be (List(new User("Hans", List(),List()),new User("Peter", List(),List())))
        notEmpty.currentUser should be (new User("Hans", List(),List()))
      }
    }

    "add user and setCurrentuser" should {
      var userList = new UserList()
      userList = userList.addUser("Hans")
      userList = userList.setCurrentUser(0)
      "user be added" in {
        userList.userList.isEmpty should be(false)
        userList.currentUser should be(new User("Hans",List(),List()))
      }
    }

    "remove user" should {
      var notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
      notEmpty = notEmpty.removeUser("Hans")
      "user get removed" in {
        notEmpty.userListLength() should be(1)
      }
    }

    "switch cards " should{
      var notEmpty = new UserList(List(new User("Hans", List(card),List(card2)),new User("Peter", List(),List())),new User("Hans", List(card),List(card2)))
      notEmpty = notEmpty.switchCards(0,0)
      "switch cards from hand to cards on table" in {
        notEmpty.currentUser.userCardStackTable(0) should be(card)
        notEmpty.currentUser.userCardStackHand(0) should be(card2)
      }
    }

    "updateList " should {
      var notEmpty = new UserList(List(new User("Hans", List(),List()),new User("Peter", List(),List())),new User("Hans", List(),List()))
      notEmpty = notEmpty.updateList(new User("Hans", List(card), List(card2)))
      "update the exsisting user to the new user" in {
        notEmpty.currentUser.emptyHand() should be(false)
      }
    }
  }
}