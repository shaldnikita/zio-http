package ru.shaldnikita.biba.controllers.protocol

import io.circe.generic.semiauto.deriveEncoder
import ru.shaldnikita.biba.domain.Beer

case class BeerResponse(beer: Seq[Beer])

object BeerResponse {
  implicit val encoder = deriveEncoder[BeerResponse]
}
