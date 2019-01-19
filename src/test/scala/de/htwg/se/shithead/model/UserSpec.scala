package de.htwg.se.shithead.model

import org.scalatest.{Matchers, WordSpec}

class UserSpec extends WordSpec with Matchers {
  "An User" when {
    "created with name and empty list" should {
      val user = new User("Hans", List(), List())
      "have rank Two, suite Diamond and visibility false" in {
        user.name should be("Hans")
        user.stackTable.isEmpty should be(true)
        user.stackHand.isEmpty should be(true)
      }
    }
  }
}