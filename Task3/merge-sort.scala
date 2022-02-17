import zio.{ExitCode, Fiber, IO, Task, URIO, ZIO}
import zio.ZIO.{absolve, effect}
import zio.clock.{Clock, currentTime}
import zio.duration.Duration
import zio.stream.ZStream

import scala.::
import scala.io.StdIn._


object Main extends App {
  val t1 = System.nanoTime
  def min(a: Int, b: Int): Int = {
    if(a<b) return a
    else return b
  }

  def solve(l: List[Int] ) : List[Int]={
     if(l.length>2) {
       subarraySort(solve(l.splitAt(l.length/2)._1) ,solve(l.splitAt(l.length/2)._2), List())
     }
     else if(l.length==2) {
       subarraySort(l.splitAt(1)._1, l.splitAt(1)._2, List())
     }
     else{
       l
     }
  }

  def subarraySort(l1: List[Int], l2: List[Int], res : List[Int]): List[Int]={
    if(l1.length==0 && l2.length==0)
      return res
    else if(l1.length==0 && l2.length!=0)
      subarraySort(l1, List(), res:::l2)
    else if(l1.length!=0 && l2.length==0)
      subarraySort(List(), l2, res:::l1)
    else if(l1.head < l2.head)
      subarraySort(l1.tail, l2, res:::List(l1.head))
    else
      subarraySort(l1, l2.tail, res:::List(l2.head))
  }
   val a = List(1,2,3,4,5,4,3,2,-10)
   println(solve(a))

   println((System.nanoTime - t1) / 1e9d)
}
