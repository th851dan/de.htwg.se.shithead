package de.htwg.se.shithead.view.Gui

import java.text.NumberFormat

import de.htwg.se.shithead.controller.GameState._
import de.htwg.se.shithead.controller.{CellChanged, ControllerInterface, GameState}

import scala.swing._
import scala.swing.event._

class Gui(controller: ControllerInterface) extends Frame {

  listenTo(controller)
  title = "Shithead"
  val maxTextFieldSize = new Dimension(100, 30)
  val windowSize = new Dimension(512, 512)
  val windowColor = java.awt.Color.RED
  val emptyBorder = Swing.EmptyBorder(10, 10, 10, 10)
  val linedBorder = Swing.LineBorder(java.awt.Color.BLACK)
  val output = new TextArea(text = "------------------This is Shitead------------------") {
    editable = false
  }

  contents = new GridPanel(1, 2) {
    val textCard = new ScrollPane(output) {
      border = Swing.EtchedBorder
      foreground = java.awt.Color.RED
    }
    val currentUser = new Label("add users first") {
      border = Swing.EtchedBorder
    }
    val playCardText = new FormattedTextField(NumberFormat.getIntegerInstance) {
      maximumSize = maxTextFieldSize
      border = Swing.EtchedBorder
    }

    val buttons: GridPanel = new GridPanel(3, 1) {
      border = emptyBorder
      var cards: List[Int] = List()
      val addCard = new Button("add card") {
        border = Swing.EtchedBorder
      }
      val playCard = new Button("play card(s)") {
        border = Swing.EtchedBorder
      }

      contents += currentUser

      contents += new GridPanel(2, 2) {
        contents += addCard
        contents += playCard
        addCard.reactions += {
          case ButtonClicked(addCard) => {
            if (cards.length < 4 && !playCardText.text.isEmpty && controller.status == DURINGGAME) {
              cards = playCardText.text.toInt :: cards
              output.text = output.text + "\n" + cards.length + " card(s) added"
            }
            else output.text = output.text + "\n Operation not available"
          }
        }

        playCard.reactions += {
          case ButtonClicked(playCard) => {
            if (!cards.isEmpty) {
              controller.playCard(cards)
            } else output.text = output.text + "\nadd cards first"
          }
        }
      }

      contents += playCardText
    }

    val text = new BoxPanel(Orientation.Vertical) {
      border = emptyBorder
      contents += textCard
    }
    contents += buttons
    contents += text
  }

  menuBar = new MenuBar {
    contents += new Menu("menu") {
      border = Swing.LineBorder(java.awt.Color.BLACK)
      contents += new Menu("about") {
        contents += new Label("written by Marius and the only FlipperyLipp")
      }
      contents += new Separator()
      contents += new MenuItem(Action("exit") {
        System.exit(0)
      })
    }

    contents += new Menu("Game") {
      border = Swing.LineBorder(java.awt.Color.BLACK)

      contents += new MenuItem(Action("add user") {
        var r = Dialog.showInput(new BoxPanel(Orientation.Horizontal), "Enter user: ", initial = "")
        r match {
          case Some(r) => if (!controller.add(r)) output.text = output.text + "\n User not added"
          case None =>
        }

      })

      contents += new MenuItem(Action("start game") {
        val card1 = new FormattedTextField(NumberFormat.getIntegerInstance)
        val card2 = new FormattedTextField(NumberFormat.getIntegerInstance)
        if (controller.status != BEFORESTART && controller.getUserListLength() < 2) Dialog.showMessage(new FlowPanel(), "Operation not available")
        else {
          val x = Dialog.showConfirmation(new FlowPanel, "U're sure?", optionType = Dialog.Options.YesNo)
          x match {
            case Dialog.Result.Yes => if (controller.begin()) {
              while (controller.status != DURINGGAME) {
                val l = Dialog.showConfirmation(new BoxPanel(Orientation.Horizontal)
                  , controller.getCurrentUserName() + " you want to swap cards?", optionType = Dialog.Options.YesNo)
                if (l == Dialog.Result.Yes) {
                  changeCards()
                } else {
                  controller.setNextUser()
                  controller.compareToStartUser()
                }
              }
            } else output.text = output.text + "\noperation failed"
            case _ =>
          }
        }
      })

      contents += new MenuItem(Action("redo") {
        controller.redo()
      })

      contents += new MenuItem(Action("undo") {
        controller.undo()
      })
    }

    contents += new Menu("file") {
      border = Swing.LineBorder(java.awt.Color.BLACK)
      contents += new MenuItem(Action("load") {
        controller.load
      })

      contents += new MenuItem(Action("save") {
        controller.save
      })
    }
  }

  size = new Dimension(512, 512)
  visible = true

  def changeCards(): Unit = {
    val input1 = Dialog.showInput(new BoxPanel(Orientation.Vertical), "Index Card1: ", initial = "")
    val input2 = Dialog.showInput(new BoxPanel(Orientation.Vertical), "Index Card2: ", initial = "")

    if (!input1.get.isEmpty && !input1.get.isEmpty)
      if (controller.changeCards(input1.get.toInt - 1, input2.get.toInt - 1)) Dialog.showMessage(new FlowPanel(), "Opeartion successful")
      else showError()
    else showError()
  }

  def showError(): Unit = Dialog.showMessage(new FlowPanel(), "Operation failed")

  def updateOutput(): Boolean = controller.status match {
    case BEFORESTART => {
      output.text = output.text + "\n" + GameState.answer(BEFORESTART)
      true
    }
    case USERREMOVED => {
      output.text = output.text + "\n" + USERREMOVED
      controller.status = BEFORESTART
      true
    }
    case PLAYEDCARDS => {
      output.text = output.text + "\n" + GameState.answer(PLAYEDCARDS)
      if (!controller.cardStack.tableStack.isEmpty()) {
        output.text = output.text + "\n" + controller.getPlayedCard()
      }
      else println("a 10 or 4 of same kind")
      controller.status = DURINGGAME
      output.text = output.text + "\n" + controller.build(true)
      output.text = output.text + "\n" + controller.getCurrentUserName() + " it's your turn"
      true
    }
    case PICKEDUPCARDS => {
      output.text = output.text + "\n" + GameState.answer(PICKEDUPCARDS)
      output.text = output.text + "\n" + controller.buildAll(true)
      controller.status = DURINGGAME
      true
    }
    case BEGIN => {
      output.text = output.text + "\n" + controller.build(true)
      true
    }
    case DURINGGAME => {
      output.text = output.text + "\n" + GameState.answer(DURINGGAME)
      output.text = output.text + "\n" + controller.build(true)
      true
    }
    case FINISHED => {
      output.text = output.text + "\n" + GameState.answer(FINISHED)
      true
    }
    case GAMESTARTS => {
      output.text = output.text + "\n" + controller.build(false)
      output.text = output.text + "\n" + controller.getCurrentUserName() + GameState.answer(GAMESTARTS)
      controller.status = DURINGGAME
      true
    }
    case _ =>
      output.text = output.text + "\n " + GameState.answer(controller.status)
      true
  }

  reactions += {
    case event: CellChanged => updateOutput()
  }

}
