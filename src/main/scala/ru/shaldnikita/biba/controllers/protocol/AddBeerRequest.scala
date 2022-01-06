package ru.shaldnikita.biba.controllers.protocol

import io.circe.Decoder
import io.circe.generic.semiauto._
import io.scalaland.chimney.dsl.TransformerOps
import ru.shaldnikita.biba.domain.{Beer, Location}

case class AddBeerRequest(name: String, price: Long, location: Location)

object AddBeerRequest {
  implicit val addBeerRequestDecoder: Decoder[AddBeerRequest] = deriveDecoder[AddBeerRequest]

  def toBeer(addBeerRequest: AddBeerRequest) = addBeerRequest.into[Beer].transform
}
