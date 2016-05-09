package net.xngns

import spire.math.Rational
import spire.syntax.eq._

/**
  * A translation of the code examples from
  *
  * "Unbounded Spigot Algorithms for the Digits of Pi"
  *
  * by Jeremy Gibbons, 2006
  *
  * [[https://www.cs.ox.ac.uk/jeremy.gibbons/publications/spigot.pdf]]
  */
package object spigot {

  def stream[A, B, C](next: B => C,
                      safe: (B, C) => Boolean,
                      prod: (B, C) => B,
                      cons: (B, A) => B,
                      z: B,
                      s: Stream[A]): Stream[C] = {
    val y = next(z)
    if (safe(z, y))
      y #:: stream(next, safe, prod, cons, prod(z, y), s)
    else
      stream(next, safe, prod, cons, cons(z, s.head), s.tail)
  }

  type LFT = (Rational, Rational, Rational, Rational)

  def extr(l: LFT, x: Rational): Rational = l match {
    case (q, r, s, t) =>
      (Rational(q) * x + Rational(r)) /
      (Rational(s) * x + Rational(t))
  }

  val unit: LFT = (1, 0, 0, 1)

  def comp(a: LFT, b: LFT): LFT = (a, b) match {
    case ((q, r, s, t), (u, v, w, x)) =>
      (q * u + r * w, q * v + r * x, s * u + t * w, s * v + t * x)
  }

  // "A Streaming Algorithm for the Digits of Pi"
  def pi: Stream[Rational] = {
    val init = unit
    def lfts: Stream[LFT] = {
      def loop(k: Rational): Stream[LFT] =
        (k, 4 * k + 2, Rational(0), 2 * k + 1) #:: loop(k + 1)
      loop(1)
    }
    def next(z: LFT): Rational             = extr(z, 3).floor
    def safe(z: LFT, n: Rational): Boolean = extr(z, 4).floor === n
    def prod(z: LFT, n: Rational): LFT     = comp((10, -10 * n, 0, 1), z)
    def cons(z: LFT, zPrime: LFT): LFT     = comp(z, zPrime)
    stream(next, safe, prod, cons, init, lfts)
  }

  // "More Streaming Algorithms for the Digits of Pi: Lambert's expression"
  def piL: Stream[Rational] = {
    val init: (LFT, Rational) = ((0, 4, 1, 0), 1)
    def lfts: Stream[LFT] = {
      def loop(i: Rational): Stream[LFT] =
        (2 * i - 1, i * i, Rational(1), Rational(0)) #:: loop(i + 1)
      loop(1)
    }
    def next(t: (LFT, Rational)): Rational = t match {
      case ((q, r, s, t), i) =>
        val x = 2 * i - 1
        val y = (q * x + r) / (s * x + t)
        y.floor
    }
    def safe(t: (LFT, Rational), n: Rational): Boolean = t match {
      case ((q, r, s, t), i) =>
        val x = 5 * i - 2
        val y = (q * x + 2 * r) / (s * x + 2 * t)
        y.floor === n
    }
    def prod(t: (LFT, Rational), n: Rational) = t match {
      case (z, i) => (comp((10, -10 * n, 0, 1), z), i)
    }
    def cons(t: (LFT, Rational), zPrime: LFT) = t match {
      case (z, i) => (comp(z, zPrime), i + 1)
    }
    stream(next, safe, prod, cons, init, lfts)
  }
}
