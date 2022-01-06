package ru.shaldnikita.biba.service

import ru.shaldnikita.biba.domain.Beer
import ru.shaldnikita.biba.service.impl.BeerServiceImpl
import zio.macros.accessible
import zio.{Has, Task, ULayer, ZLayer}


@accessible
object BeerService {

  type BeerService = Has[Service]

  trait Service {
    def getBeer(): Task[Seq[Beer]]

    def addBeer(beer: Beer): Task[Beer]
  }

  val live: ULayer[BeerService] = ZLayer.succeed(new BeerServiceImpl())
}
