package ElephantStore

// case class State(val stateCode: String, stateName: String, lone)
// case class StateTax (val state: State, val tax: Double)

object StateTax {

}

sealed trait State {
  val stateCode: String
  val tax: Double
}

object State {
  case object CA extends State {
    override val stateCode: String = "CA"
  }

  case object UT extends State {
    override val stateCode: String = "UT"
  }

  def parse(strStateCode: String): Option[State] =
    strStateCode match {
      case "CA" => Some(CA)
      case _ => None
    }
}
