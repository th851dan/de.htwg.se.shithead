package de.htwg.se.shithead

import com.google.inject.AbstractModule
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.model._
import de.htwg.se.shithead.model.cardStackComponent.baseImp.CardStack
import de.htwg.se.shithead.model.stackComponent.baseImp.Stack
import de.htwg.se.shithead.model.userListComponent.baseImp.UserList
import net.codingwell.scalaguice.ScalaModule

class ShitHeadModule extends AbstractModule with ScalaModule {

  val defaultCardStack: CardStackInterface = new CardStack(getCards(true), getCards(false), false, true)
  val defaultUserList: UserListInterface = new UserList(List(), User("", List(), List()))

  override def configure(): Unit = {
    bind[ControllerInterface].to[Controller]
    bind[UserListInterface].to[UserList]
    bind[CardStackInterface].to[CardStack]
    bind[UserList].toInstance(new UserList(List(), User("", List(), List())))
    bind[CardStack].toInstance(new CardStack(getCards(true), getCards(false), false, true))

    bind[FileIOInterface].to[fileIoComponent.fileToJsonImpl.FileIO]
  }

  def getCards(b: Boolean): Stack = {
    if (b) {
      val suites = Set(Spade, Heart, Club, Diamond)
      val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
      new Stack(for (r <- ranks; s <- suites) yield Card(r, s, false), true).shuffle()
    } else new Stack(List(), false)
  }
}
