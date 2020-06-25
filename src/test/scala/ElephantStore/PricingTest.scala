package ElephantStore

import org.specs2.mutable._

class PricingTest extends SpecificationWithJUnit {
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
      getPriceWithTax(List(ItemDto(1, 1, 1)), "NV") === Some(1.0800)
      getPriceWithTax(List(ItemDto(1, 1, 1)), "TX") === Some(1.0625)
      getPriceWithTax(List(ItemDto(1, 1, 1)), "AL") === Some(1.0400)
    }
  }

  private def getTotalPrice(listOfItems: List[ItemDto], stateCode: String = "UT") = {
    new Pricing().get(listOfItems, stateCode).totalPrice
  }

  private def getPriceWithTax(listOfItems: List[ItemDto], stateCode: String = "UT") = {
    new Pricing().get(listOfItems, stateCode).priceWithTax
  }
}
