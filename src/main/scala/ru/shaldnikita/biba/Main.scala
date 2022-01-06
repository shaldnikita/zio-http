package ru.shaldnikita.biba

import ru.shaldnikita.biba.controllers.BeerController
import ru.shaldnikita.biba.service.BeerService
import zio.{ExitCode, Has, URIO, ZIO}

object Main extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {


    BeerController.route.provideCustomLayer(
      BeerController.live
    )



  }
}
