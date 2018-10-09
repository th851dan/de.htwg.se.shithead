package de.htwg.se.shithead.model

sealed abstract class Suite
case object Spade extends Suite
case object Heart extends Suite
case object Club extends Suite
case object Diamond extends Suite