/*
 * Copyright 2010 Gregor N. Purdy, Sr.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.exoprax.graph

import java.util.Random;
import org.testng.annotations.Test

class RandomGraph {
  
  val N = 20000
  val P = 0.00019
  
  @Test
  def testRandom() : Unit = {
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
