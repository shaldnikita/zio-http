package ru.shaldnikita.biba

import ru.shaldnikita.biba.controllers.BeerController
import ru.shaldnikita.biba.service.BeerService
import zhttp.http.{Http, Request, Response}
import zhttp.service.Server
import zio.{ExitCode, Has, URIO, ZIO}

object Main extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {


    val route: ZIO[zio.ZEnv, Nothing, Http[Has[BeerService.Service], Throwable, Request, Response[Any, Nothing]]] = BeerController.route.provideCustomLayer(
      BeerController.live
    )


    for {
      route <- route.map(_.silent.provideCustomLayer(BeerService.live))
      server <- Server.start(8080, route).exitCode
    } yield server

  }
}
