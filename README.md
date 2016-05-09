# spigot

A translation of the code examples from ["Unbounded Spigot Algorithms for the Digits of Pi"](https://www.cs.ox.ac.uk/jeremy.gibbons/publications/spigot.pdf) (Gibbons, 2006) from Haskell to Scala.

## Usage

```
scala> import net.xngns.spigot._
import net.xngns.spigot._

scala> val fiftyDigits = pi.take(50)
fiftyDigits: scala.collection.immutable.Stream[spire.math.Rational] = Stream(3, ?)

scala> fiftyDigits.toList
res0: List[spire.math.Rational] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1)

scala> fiftyDigits.map(_.toInt).toList
res1: List[Int] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1)

scala> val fiftyDigitsL = piL.take(50)
fiftyDigitsL: scala.collection.immutable.Stream[spire.math.Rational] = Stream(3, ?)

scala> fiftyDigitsL.toList
res2: List[spire.math.Rational] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1)

scala> fiftyDigits == fiftyDigitsL
res3: Boolean = true
```
