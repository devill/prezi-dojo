package ElephantStore

class Pricing {
  def get(itemList: List[ItemDto], stateCode: String): PricingDto = {
    val totalPrice = this.totalPrice(itemList)

    PricingDto(
      totalPrice,
      totalPrice * (if(stateCode == "CA") 1.0825 else 1.0685)
    )
  }

  private def totalPrice(itemList: List[ItemDto]): Double = itemList.map(item => item.price * item.quantity).sum
}
