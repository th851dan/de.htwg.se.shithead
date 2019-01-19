package de.htwg.se.shithead.model.fileIoComponent.fileToXmlImpl

import java.io.{File, PrintWriter}

import com.google.inject.Guice
import de.htwg.se.shithead.ShitHeadModule
import de.htwg.se.shithead.controller.ControllerInterface
import de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.Controller
import play.api.libs.json.{JsValue, Json}

import scala.io.Source
import scala.xml.PrettyPrinter

object test {
  def main(args: Array[String]): Unit = {
    val inj = Guice.createInjector(new ShitHeadModule)
    val con = inj.getInstance(classOf[Controller])
    val source = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    con.load
  }
}
