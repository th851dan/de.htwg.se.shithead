package de.htwg.se.shithead.controller

import com.google.inject.Guice
import de.htwg.se.shithead.ShitHeadModule
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.model.{Card, Spade, Two, User}

object test {
  def main(args: Array[String]): Unit = {
    var inj = Guice.createInjector(new ShitHeadModule)
    val controller = inj.getInstance(classOf[Controller])
    controller.add("hans")
    controller.add("peter")
    controller.getUserListLength()
  }
}
