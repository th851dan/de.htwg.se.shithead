import org.scalatest._

class CardSpec extends WordSpec {
    "A Card" when {
        "created with rank, suite and visibility" should {
            "have rank not null" in {
                assert(Card.rank.empty.size == 0)
            }
        }
    }
}