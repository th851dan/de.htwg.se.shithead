package de.htwg.se.shithead.controller

import com.google.inject.Guice
import de.htwg.se.shithead.ShitHeadModule
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.shithead.controller.GameState._

class ControllerSpec extends WordSpec with Matchers{
  "In controller" when {
    var inj = Guice.createInjector(new ShitHeadModule)
    val controller = inj.getInstance(classOf[Controller])

    "add and remove " should {
      "add users and remove from/to controller.userlist" in {
        controller.add("hans")
        controller.add("peter")
        controller.getUserListLength() should be(2)
      }
    }

    "begin" should {
      "initilize added users" in {
        controller.add("hans")
        controller.add("peter")
        controller.begin() should be(true)
        controller.userCount should be(2)
        controller.userList.currentUser.userCardStackTable.isEmpty should be (false)
      }
    }

    "set nextuser compare to Startuser and getCard" should {
      controller.add("hans")
      controller.add("peter")
      controller.begin()
      var user = controller.getCurrentUserName()
      "set to next user in userlist compare to startuser and getcard of currentuser at index" in {
        controller.setNextUser()
        controller.getCurrentUserName should not be(user)
        controller.compareToStartUser
        controller.status should be(BEGIN)
        controller.getCard(0) should not be(empty)
      }
    }

    "change cards" should {
      "change cards of user from hand to table both ways" in {
        controller.add("hans")
        controller.add("peter")
        controller.begin()
        controller.changeCards(1,1) should be(true)
        controller.changeCards(10,10) should be(false)
        controller.status = FINISHED
        controller.changeCards(1,1) should be(false)
      }
    }

    "build getCurrentuserName getPlayedCard anf currentUserHasHand" should {
      controller.add("hans")
      controller.add("peter")
      controller.begin()
      "build astring, return currentusername, gettop cardstacktable card and should return if user has more than 3 elm. on hand " in {
        controller.build(true) should not be(empty)
        (controller.buildAll(true).length > controller.build(true).length) should be(true)
        controller.getCurrentUserName() should be(controller.userList.currentUser.NAME)
        controller.getPlayedCard() should be("")
        controller.status = DURINGGAME
        controller.playCard(List(0))
        controller.getPlayedCard().isEmpty should be(false)
      }
    }
  }

}
