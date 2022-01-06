package ru.shaldnikita.biba.controllers.protocol.marshallers

import akka.http.scaladsl.marshalling.Marshaller
import ru.shaldnikita.biba.Environment
import zio.{RIO, Runtime, Task, ZEnv}

trait ZIOMarshallers {
  this: Runtime[Environment] =>

  implicit def zio2Marshaller[A, B](
                                     implicit m1: Marshaller[A, B],
                                     m2: Marshaller[Throwable, B]): Marshaller[RIO[Environment, A], B] =
    Marshaller { implicit ec =>
      a =>
        unsafeRun(a.fold(e => m2(e), a => m1(a)))
    }

}