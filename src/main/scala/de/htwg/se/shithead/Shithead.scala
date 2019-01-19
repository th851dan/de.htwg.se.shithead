package de.htwg.se.shithead

import com.google.inject.Guice
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import de.htwg.se.shithead.view.Gui.Gui
import de.htwg.se.shithead.view.Tui

import scala.io.StdIn.readLine

object Shithead {

  def main(args: Array[String]) {
    println("This is Shithead!")
    val injector = Guice.createInjector(new ShitHeadModule)

    val con = injector.getInstance(classOf[Controller])
    val tui: Tui = new Tui(con)
    val gui: Gui = new Gui(con)


    var input: String = ""
    do {
      input = readLine
      // Controller processes input!
      tui.eval(input)
    } while (input != "q" && input != "Q")
  }
}