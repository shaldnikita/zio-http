package ru.shaldnikita.biba.domain

import io.circe.generic.semiauto.deriveEncoder

case class Beer(name: String, price: Long, location: Location)

object Beer {
  implicit val beerEncoder = deriveEncoder[Beer]
}
