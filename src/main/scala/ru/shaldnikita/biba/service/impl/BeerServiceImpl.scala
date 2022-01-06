package ru.shaldnikita.biba.service.impl

import ru.shaldnikita.biba.domain.{Beer, Location}
import ru.shaldnikita.biba.service.BeerService
import zio.{Task, ZIO}

import scala.collection.mutable

class BeerServiceImpl extends BeerService.Service {

  private val beer = mutable.Map[String, Beer]("asd" -> Beer("asd", 123, Location("asd", "asd", None)))

  override def getBeer(): Task[Seq[Beer]] = ZIO.effectTotal(beer.values.toSeq)

  override def addBeer(beer: Beer): Task[Beer] = ZIO.effectTotal {
    this.beer.addOne(beer.name -> beer)
    beer
  }
}
