package de.htwg.se.shithead.model

case class User(uuid: String = java.util.UUID.randomUUID.toString,
                name: String, 
                userCardStack: List[Card])