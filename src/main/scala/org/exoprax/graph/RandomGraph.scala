
package org.exoprax.graph

import java.util.Random;

object RandomGraph {
  
  val N = 20000
  val P = 0.00019
  
  def main(args : Array[String]) : Unit = {
    val g = Graph(N, P)
    
//    println(g.edges)
//    println(g.distances(6).filter(o => !o._2.isEmpty))
    
    val rand = new Random()
    
    val i = rand.nextInt.abs % N;
    
    println("N = " + N)
    println("P = " + (P formatted "%8.6f"))
    println("i = " + i)
    println()
    
    val id = g.inverseDistances(i)
    
//    println(id)
    
    // http://en.wikipedia.org/wiki/N-sphere
    // V_n / S_{n-1} = R / n
    // n = R / ( V_n / S_{n-1} )
      
    println("radius\tcirc.\tarea\tn")
    println("------\t------\t------\t------")

    var area = 0
    
    for (radius <- 1 to N - 1) {
      val circumference = id.get(Some(radius)) match {
        case None => 0
        case Some(x) => x.size
      }
      
      if (circumference > 0) {
        area = area + circumference
      
        val n : Double = radius.toDouble / ( area.toDouble / circumference.toDouble )
        
        val f = String.format("%s\t%s\t%s\t%s",
                              radius formatted "%6d",
                              circumference formatted "%6d",
                              area formatted "%6d",
                              n formatted "%6.2f")
        
        println(f)
      }
    }
  }
  
}
