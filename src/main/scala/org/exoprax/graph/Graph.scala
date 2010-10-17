package org.exoprax.graph

import java.util.Random;
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.collection.mutable.Queue

object Graph {

  def apply(n : Int, p : Double) = new Graph(n, p)
  
}

class Graph private (n : Int, p : Double) {
    val r = new Random()
  
    val g = Map[Int, Set[Int]]()
  
    for (i <- 0 to n - 1) {
      g(i) = Set[Int]()
      
      for (j <- 0 to i - 1) {
        if (r.nextDouble <= p) {
          g(i) += j
          g(j) += i
        }
      }
    }

    def size = n
    
    def hasEdge(i : Int, j : Int) = g(i).contains(j)
    
    def edges : List[Set[Int]] = {
      val v = for (i <- 0 to n - 1; j <- g(i); if j <= i) yield Set(i, j)
      v.toList
    }
    
    def neighbors(i : Int) : Set[Int] = g.get(i) match {
      case Some(s) => s
      case None => Set[Int]()
    }
        
    def distances(i : Int) : Map[Int, Option[Int]] = {
      val d = Map[Int, Option[Int]]()
      var q = new Queue[(Int, Int)]
      
      q.enqueue((i, 0))
      
      for (j <- 0 to n - 1) {
        d(j) = None
      }

      while (q.size > 0) {
        val p = q.dequeue
        val j = p._1
        val x = p._2
        
        d(j) match {
          case None => {
//            println("Just determined distance from " + i + " to " + j + " is " + x)
            d(j) = Some(x)
            for (k <- neighbors(j)) {
//              println("Enqueuing neighbor " + k + " as possibly having distance " + (x + 1) + " (unless it turns out to have been previously determined)")
              q.enqueue((k, x + 1))
            }
          }
          case Some(y) => {
//            println("Previously determined distance from " + i + " to " + j + " is " + y)
          }
        }
      }
      
      d
    }
    
    def inverseDistances(i : Int) : Map[Option[Int], Set[Int]] = {
      val d = Map[Option[Int], Set[Int]]()

      for (p <- distances(i)) {
        val j = p._1
        val x = p._2
        
        if (!d.contains(x)) {
          d(x) = Set[Int]()
        }
        
        d(x) += j
      }
      
      d
    }
    
}
