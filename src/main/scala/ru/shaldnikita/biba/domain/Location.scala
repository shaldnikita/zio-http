package ru.shaldnikita.biba.domain

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Location(name: String, city: String, metro: Option[String])

object Location {
  implicit val encoder = deriveEncoder[Location]
  implicit val decoder = deriveDecoder[Location]
}
