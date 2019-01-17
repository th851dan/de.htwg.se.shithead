package de.htwg.se.shithead.view

import de.htwg.se.shithead.controller.Controller
import javax.swing.border.EmptyBorder

import scala.swing._
import scala.swing.event._

class Gui(controller: Controller) extends SimpleSwingApplication{

  override def top: Frame = new MainFrame() {
     title = "Shithead"

      object add extends Button {text = "ADD"}
      object start extends Button {text = "start game"}
      var b:BoxPanel = new BoxPanel(Orientation.Horizontal) {
        contents += new TextField(columns = 20)
      }

      contents = new BoxPanel(Orientation.Vertical) {
        contents += new Label("User:")
        contents += new TextField(columns = 20)
        contents += add
        contents += start
      }


      listenTo(add,start,b)
      reactions += {
        case ButtonClicked(add) => println("hoi")
        case ButtonClicked(start) => println("start")
      }
  }
}
