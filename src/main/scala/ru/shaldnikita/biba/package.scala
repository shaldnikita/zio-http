package ru.shaldnikita

import ru.shaldnikita.biba.service.BeerService
import zio.ZEnv

package object biba {

  type Environment = BeerService.BeerService with ZEnv

}
