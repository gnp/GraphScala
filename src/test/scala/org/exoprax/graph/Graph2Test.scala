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
