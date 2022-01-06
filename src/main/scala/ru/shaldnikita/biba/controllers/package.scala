package ru.shaldnikita.biba

import ru.shaldnikita.biba.service.BeerService
import ru.shaldnikita.biba.service.BeerService.BeerService
import zhttp.http.{Http, RHttpApp, Request, Response, UHttp}
import zio.{Has, UIO}

package object controllers {

  type Route = UIO[Http[Has[BeerService.Service], Throwable, Request, Equals]]

}
