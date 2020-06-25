package ElephantStore

case class StateDto(
                     code:String,
                     tax:Double
                )

object StateDto {
    def get(stateCode:String): Option[StateDto] = {
      val tax = stateCode match {
        case "CA" => Some(8.25)
        case "NV" => Some(8.00)
        case "TX" => Some(6.25)
        case "AL" => Some(4.00)
        case "UT" => Some(6.85)
        case _ => None
      }

      tax.map { x =>
        StateDto(stateCode, x)
      }
    }
}

