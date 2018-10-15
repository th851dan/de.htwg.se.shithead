package de.htwg.se.shithead.model

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
    "A Card" when {
        "new" should {
            val card = Card()
        }
    }
}