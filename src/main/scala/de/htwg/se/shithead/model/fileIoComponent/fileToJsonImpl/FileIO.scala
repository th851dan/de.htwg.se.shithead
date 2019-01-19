package de.htwg.se.shithead.model.fileIoComponent.fileToJsonImpl

import java.io.{File, PrintWriter}

import de.htwg.se.shithead.model._
import de.htwg.se.shithead.model.cardStackComponent.baseImp.CardStack
import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import de.htwg.se.shithead.model.userListComponent.baseImp.UserList
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {


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
  }

  def loadCardStack(result: JsLookupResult): CardStackInterface = new CardStack(
    new Stack(loadList((result \ "available" \ "cards")), (result \ "available" \ "isValid").as[Boolean]),
    new Stack(loadList((result \ "table" \ "cards")), (result \ "table" \ "isValid").as[Boolean]),
    (result \ "reverse").as[Boolean],
    (result \ "valid").as[Boolean]
  )

  def loadList(result: JsLookupResult): List[Card] = {
    var list: List[Card] = List()
    for (r <- 0 to (result \ "size").as[Int] - 1)
      list = (new Card(getRank(((result \ "stack") (r) \ "rank").as[String]),
        getSuite(((result \ "stack") (r) \ "suite").as[String]),
        (((result \ "stack") (r) \ "visibility").as[Boolean]))) :: list
    list
  }

  def loadUser(result: JsValue): User = new User(
    (result \ "name").as[String],
    loadList((result \ "handStack")),
    loadList((result \ "tableStack")))

  def loadListOfUsers(result: JsLookupResult): List[User] = {
    var list: List[User] = List()
    for (i <- 0 to (result \ "size").as[Int] - 1) list = loadUser((result \ "list") (i)) :: list
    list
  }

  def getUserList(result: JsLookupResult): UserListInterface = new UserList(
    loadListOfUsers((result)),
    new User((result \ "currentUser" \ "name").as[String], loadList((result \ "currentUser" \ "handStack")), loadList((result \ "currentUser" \ "tableStack")))
  )

  override def load(): (CardStackInterface, UserListInterface) = {
    var cardStack: CardStackInterface = null
    var userList: UserListInterface = null
    val source = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    cardStack = loadCardStack((json \ "cardStack"))
    userList = getUserList((json \ "userList"))

    (cardStack, userList)
  }

  override def save(cardStack: CardStackInterface, userList: UserListInterface): Unit = {
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(BothToJson(cardStack, userList)))
    pw.close()
  }

  def BothToJson(cardStack: CardStackInterface, userList: UserListInterface): JsValue = {
    Json.obj(
      "userList" -> Json.obj(

        "currentUser" -> saveUser(userList.currentUser),
        "size" -> userList.userList.length,
        "list" -> Json.toJson(for (u <- userList.userList) yield saveUser(u))
      ),
      "cardStack" -> Json.obj(
        "valid" -> cardStack.valid,
        "reverse" -> cardStack.reverse,

        "table" -> Json.obj(
          "isValid" -> cardStack.tableStack.isValid,
          "cards" -> getCardList(cardStack.tableStack.cardStack)
        ),

        "available" -> Json.obj(
          "isValid" -> cardStack.cardStack.isValid,
          "cards" -> getCardList(cardStack.cardStack.cardStack)
        )
      )
    )
  }

  def saveUser(user: User): JsObject = Json.obj(
    "name" -> user.NAME,
    "tableStack" -> getCardList(user.userCardStackTable),
    "handStack" -> getCardList(user.userCardStackHand)
  )

  def getCardList(cardStack: List[Card]) = Json.obj(
    "size" -> (cardStack.length),
    "stack" -> Json.toJson(for (card <- cardStack) yield Json.obj(
      "visibility" -> JsBoolean(card.visibility),
      "suite" -> JsString(card.suite.toString),
      "rank" -> JsString(card.rank.toString)
    ))
  )

}