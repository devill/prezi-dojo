package Main

object PrimeFactors {
  def factorize(value: Int) : List[Int] = {
    if (value % 3 == 0) factorize(value/3) :+ 3 else
    if (value % 2 == 0) factorize(value/2) :+ 2 else
    if (value > 1) List(value) else List.empty
  }

}
