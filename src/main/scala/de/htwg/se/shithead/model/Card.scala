package de.htwg.se.shithead.model

case class Card(rank: Rank,
                suite: Suite,
                visibility: Boolean) {
  def setVisibility(boolean: Boolean): Card = copy(rank, suite, boolean)

  override def toString: String = "Card: " + suite + " " + rank
}
