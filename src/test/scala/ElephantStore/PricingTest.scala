package ElephantStore

import ElephantStore.Core.{ItemDto, Pricing, StateDto, StateRepository}
import org.specs2.mutable._


class StateTestRepository extends StateRepository {
  def get(stateCode:String): Option[StateDto] = {
    stateCode match {
      case "CA" => StateDto(stateCode, Some(8.25))
      case "UT" => StateDto(stateCode, Some(6.85))
      case _ => None
    }
  }
}

class PricingTest extends SpecificationWithJUnit {
  implicit val stateRepository:StateRepository = new StateTestRepository

  "get price" should {
    "return zero for no items at all" in {
      getTotalPrice(List.empty) === 0
    }

    "return an items price when one item in list" in {
      getTotalPrice(List(ItemDto(1, 1, 1))) === 1// 1.0685
    }

    "return the price of several items with quantity one" in {
      getTotalPrice(List(
        ItemDto(1, 1, 1),
        ItemDto(1, 2, 1)
      )) === 3 //3.2055
    }

    "return zero for no items at all" in {
      getPriceWithTax(List.empty) === Some(0)
    }

    "return an items price when one item in list" in {
      getPriceWithTax(List(ItemDto(1, 1, 1))) === Some(1.0685)
    }

    "return the price of several items with quantity one" in {
      getPriceWithTax(List(
        ItemDto(1, 1, 1),
        ItemDto(1, 2, 1)
      )) === Some(3.2055)
    }

    "return an items price when one item in list with multiple quanitity" in {
      getTotalPrice(List(ItemDto(2, 1, 1))) === 2
    }

    "return an items price with tax when one item in list with multiple quanitity" in {
      getPriceWithTax(List(ItemDto(2, 1, 1))) === Some(1.0685 * 2)
    }

    "return the price with tax for all known states" in {
      getPriceWithTax(List(ItemDto(1, 1, 1)), "CA") === Some(1.0825)
    }

    "return none for price with tax if state is not found" in {
      getPriceWithTax(List(ItemDto(1, 1, 1)), "NOT A STATE") === None
    }
  }

  private def getTotalPrice(listOfItems: List[ItemDto], stateCode: String = "UT") = {
    new Pricing().get(listOfItems, stateCode).totalPrice
  }

  private def getPriceWithTax(listOfItems: List[ItemDto], stateCode: String = "UT") = {
    new Pricing().get(listOfItems, stateCode).priceWithTax
  }
}
