package ru.shaldnikita.biba.controllers

import io.circe.parser._
import io.circe.syntax.EncoderOps
import ru.shaldnikita.biba.controllers.protocol.{AddBeerRequest, BeerResponse}
import ru.shaldnikita.biba.service.BeerService
import zhttp.http._
import zio.macros.accessible
import zio.{Has, UIO, ULayer, ZIO, ZLayer}


@accessible
object BeerController {

  type Controller = Has[Service]

  trait Service {
    def route: UIO[Http[Has[BeerService.Service], Throwable, Request, Response[Any, Nothing]]]
  }

  val live: ULayer[Controller] = ZLayer.succeed(new Service {

    override def route = UIO.succeed(getBeer <> addBeer)

    private def getBeer = Http.collectM[Request] {
      case Method.GET -> !! / "beer" => for {
        beer <- BeerService.getBeer()
        // https://github.com/dream11/zio-http/blob/v1.0.0.0-RC21/zio-http/src/main/scala/zhttp/http/Response.scala#L145 - багуля
      } yield Response(
        Status.OK,
        Headers(HeaderNames.contentType, HeaderValues.applicationJson),
        HttpData.fromString(
          BeerResponse(beer).asJson.noSpaces
        )
      )
    }

    private def addBeer = Http.collectM[Request] {
      case req@Method.POST -> !! / "beer" =>
        for {
          addBeerE <- req.getBodyAsString.map(decode[AddBeerRequest])
          addBeer <- ZIO.fromEither(addBeerE)
          result <- BeerService.addBeer(AddBeerRequest.toBeer(addBeer))
        } yield Response.json(result.asJson.noSpaces)
    }
  }
  )

}
