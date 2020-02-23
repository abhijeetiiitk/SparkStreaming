object HelloWorld extends App {
  println("Hello world")
}

object HelloWorld {
  def main(args:Array[String]): Unit = {
    println(
      "Hello World"
    )
  }
}

def square(x: Double) = x * x
def sumOfSquare(x : Double, y: Double) = square(x) + square(y)
sumOfSquare(2,3)

/*def loop : Int = loop
loop*/

def sum(a: Int, b:Int):Int = if(a>b) 0 else a + sum(a+1,b)

def cube(x:Int) = x*x*x

def sumOfCubes(a:Int, b:Int) : Int = if(a>b) 0 else cube(a) + sumOfCubes(a+1,b)

def fact(x:Int):Int = if(x == 0) 1 else x * fact(x-1)

def sumOfFact(a:Int, b:Int): Int = if(a>b) 0 else fact(a) + sumOfFact(a+1,b)

sumOfFact(1,3)

/*def sum(f:Int => Int, a: Int, b: Int) : Int = if(a>b) 0 else f(a) + sum(f,a+1,b)*/

def id(x:Int) : Int = x


//def sumOfInts(a:Int,b:Int):Int = sum(id,a,b)
//def sumOfCube(a:Int,b:Int):Int = sum(cube,a,b)
def sumOfFacts(a:Int,b:Int):Int = sum(fact,a,b)

def sumOfInts(a:Int,b:Int):Int = sum(x=>x,a,b)
def sumOfCube(a:Int,b:Int):Int = sum(x=>x*x*x,a,b)









sumOfInts(1,3)
sumOfCube(1,3)
sumOfFacts(1,3)