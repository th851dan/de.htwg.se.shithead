package de.htwg.se.shithead.model.fileIoComponent.fileToXmlImpl

import java.io.{File, PrintWriter}

import de.htwg.se.shithead.model._
import de.htwg.se.shithead.model.cardStackComponent.baseImp.CardStack
import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import de.htwg.se.shithead.model.userListComponent.baseImp.UserList

import scala.xml.{NodeSeq, PrettyPrinter}

class FileIO extends FileIOInterface {

  val defFile: File = new File("game.xml")

  def getSuite(suite: String): Suite = suite match {
    case "Spade" => Spade
    case "Heart" => Heart
    case "Club" => Club
    case "Diamond" => Diamond
  }

  def getRank(rank: String): Rank = rank match {
    case "Two" => Two
    case "Three" => Three
    case "Four" => Four
    case "Five" => Five
    case "Six" => Six
    case "Seven" => Seven
    case "Eight" => Eight
    case "Nine" => Nine
    case "Ten" => Ten
    case "Jack" => Jack
    case "Queen" => Queen
    case "King" => King
    case "Ace" => Ace
    case _ => throw new NoSuchElementException
  }

  def getCardList(s: NodeSeq): List[Card] = {
    var li: List[Card] = List()
    for (s2 <- s \\ "card") {
      li = new Card(
        getRank((s2 \ "@rank").toString()),
        getSuite((s2 \ "@suite").toString()),
        (s2 \ "@visibility").toString().toBoolean) :: li
    }
    li
  }

  def getUserList(seq: NodeSeq, seq1: NodeSeq): UserListInterface = new UserList(
    {
      var list: List[User] = List()
      for (s <- seq \\ "user") list = new User(
        (s \ "@name").toString,
        getCardList((s \\ "user" \\ "handStack")),
        getCardList((s \\ "user" \\ "tableStack"))) :: list
      list
    }
    , new User((seq1 \ "@name").toString(), getCardList((seq1 \\ "handStack")), getCardList((seq1 \\ "tableStack")))
  )

  def getCardStack(seq: NodeSeq): CardStackInterface = new CardStack(
    new Stack(getCardList((seq \\ "stackAvailable")), (seq \\ "stackAvailable" \ "@isValid").toString.toBoolean),
    new Stack(getCardList((seq \\ "cardStack" \\ "stackOnTable")), (seq \\ "cardStack" \\ "stackOnTable" \ "@isValid").toString.toBoolean),
    (seq \ "@reverse").toString.toBoolean, (seq \ "@valid").toString.toBoolean)

  override def load(): (CardStackInterface, UserListInterface) = {
    var cardStack: CardStackInterface = null
    var userList: UserListInterface = null
    val file = scala.xml.XML.loadFile(defFile)
    userList = getUserList((file \\ "game" \\ "list" \\ "users"), (file \\ "game" \\ "list" \\ "current"))
    cardStack = getCardStack((file \\ "game" \\ "cardStack"))
    (cardStack, userList)
  }

  override def save(cardStack: CardStackInterface, userList: UserListInterface): Unit = saveString(userList, cardStack)

  def saveString(userList: UserListInterface, cardStack: CardStackInterface): Unit = {
    val pw = new PrintWriter(defFile)
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(bothToXml(cardStack, userList))
    pw.write(xml)
    pw.close()
  }

  def saveXml(userList: UserListInterface, cardStack: CardStackInterface) = scala.xml.XML.save("game.xml", bothToXml(cardStack, userList))

  def bothToXml(cardStack: CardStackInterface, userList: UserListInterface) = {
    <game>
      {<cardStack reverse={cardStack.reverse.toString} valid={cardStack.valid.toString}>
      {<stackOnTable isValid={cardStack.tableStack.isValid.toString}>
        {for {card <- cardStack.tableStack.cardStack} yield cardToXml(card)}
      </stackOnTable>

        <stackAvailable isValid={cardStack.cardStack.isValid.toString}>
          {for {
          card <- cardStack.cardStack.cardStack
        } yield cardToXml(card)}
        </stackAvailable>}
    </cardStack>
      <list>
        {<current name={userList.currentUser.NAME}>
        {<tableStack>
          {for {card <- userList.currentUser.stackTable} yield cardToXml(card)}
        </tableStack>
          <handStack>
            {for {card <- userList.currentUser.stackHand} yield cardToXml(card)}
          </handStack>}
      </current>
        <users>
          {for {user <- userList.userList} yield {
          <user name={user.NAME}>
            {<tableStack>
            {for {card <- user.stackTable} yield cardToXml(card)}
          </tableStack>
            <handStack>
              {for {card <- user.stackHand} yield cardToXml(card)}
            </handStack>}
          </user>
        }}
        </users>}
      </list>}
    </game>
  }

  def cardToXml(card: Card) = {
    <card rank={card.rank.toString} suite={card.suite.toString} visibility={card.visibility.toString}></card>
  }
}
