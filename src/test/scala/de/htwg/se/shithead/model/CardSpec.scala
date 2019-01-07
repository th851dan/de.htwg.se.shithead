package de.htwg.se.shithead.model

import org.scalatest._

class CardSpec extends WordSpec with Matchers {
    "A Card" when {
        "created with rank, suite and visibility" should {
            val card = Card(Two, Diamond, false)
            "have rank Two, suite Diamond and visibility false" in {
                card.rank should be(Two)
                card.suite should be(Diamond)
                card.visibility should be(false)
            }
        }
    }
}