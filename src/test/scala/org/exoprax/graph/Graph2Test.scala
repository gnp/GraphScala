package org.exoprax.graph

import Graph2._
import org.testng.annotations.Test

class Graph2Test {

  @Test
  def testSimple() : Unit = {
    println("TRIPLE:")
    println(1 -- 2 -> 3)
    
    println()
    println("TEST 1")
    val test1 = Graph2(1 -> 2, 2 -> 3, 3 -> 1)
    println(test1)

    println()
    println("TEST 2")
    val test2 = Graph2(1 -> 2 -> 3)
    println(test2)
    
    println()
    println("TEST 3")
    val test3 = Graph2(1 -- 2 -> 3)
    println(test3)
    
    println()
    println("TEST 4")
    val test4 = Graph2("A" -> 2)
    println(test4)
    
    println()
    println("TEST 5")
    val test5 = Graph2("Z")
    println(test5)
    
    println()
    println("TEST 6")
    val test6 = Graph2("Z", "A" -> 2, 42)
    println(test6)
    
    println()
    println("TEST 7")
    val test7 = Graph2("A", "A" -> "C", "B", "B" -- 14 -> "D")
    println(test7)

  }

}
