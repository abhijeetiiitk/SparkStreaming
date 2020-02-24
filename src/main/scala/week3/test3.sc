object Rational {
  val x = new Rational(1,2)
  x.denom
  x.numer
  val y = new Rational(2,3)

  x.add(y)
}

class Rational(x:Int, y:Int) {
  def numer = x
  def denom: Int = y

  def add(that:Rational): Rational
   = {
    new Rational(numer * that.denom + that.denom*numer,denom *  that.denom
    )
  }

  override def toString: String = numer + "/" + denom
}


