package ru.shaldnikita.biba.controllers

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.parser._
import io.circe.syntax.EncoderOps
import ru.shaldnikita.biba.controllers.protocol.marshallers.ZIOMarshallers
import ru.shaldnikita.biba.controllers.protocol.{AddBeerRequest, BeerResponse}
import ru.shaldnikita.biba.service.BeerService
import zio.macros.accessible
import zio.{Has, UIO, ULayer, ZIO, ZLayer}


@accessible
object BeerController extends FailFastCirceSupport with ZIOMarshallers {

  type Controller = Has[Service]

  trait Service {
    def route: Route
  }

  val live: ULayer[Controller] = ZLayer.succeed(new Service {



    override def route = path("beer"){
      (post & entity(as[AddBeerRequest])) { addBeer =>
          BeerService.addBeer(AddBeerRequest.toBeer(addBeer))
      } ~
        get {
          BeerService.getBeer()
        }
    }

  }
  )

}
