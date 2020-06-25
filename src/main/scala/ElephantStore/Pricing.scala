package ElephantStore

class Pricing {
  def get(itemList: List[ItemDto], stateCode: String): PricingDto = {
    val totalPrice = this.totalPrice(itemList)

    PricingDto(
      totalPrice,
      StateDto.get(stateCode).map { state => totalPrice * (1 + state.tax/100) }
    )
  }

  private def totalPrice(itemList: List[ItemDto]): Double = itemList.map(item => item.price * item.quantity).sum
}
