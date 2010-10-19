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

object Graph2 {
  
  abstract sealed class EA[A, +B](a: A)
  private case class Edge[A, +B](a: A, c: B, b: A) extends EA[A, B](a)
  private case class Node[A](a: A) extends EA[A, Unit](a)
  
  class EdgeAssoc2[A, B](x: A, y: B) {
    def -> [C](z: C): Tuple3[A, B, C] = Tuple3(x, y, z)
  }
  
  class EdgeAssoc[A](x: A) {
    def -- [B](y: B): EdgeAssoc2[A, B] = new EdgeAssoc2(x, y)
  }
  implicit def any2EdgeAssoc[A](x: A): EdgeAssoc[A] = new EdgeAssoc(x)

  implicit def tuple2EA[A](x: (A, A)): EA[A, Unit] = Edge(x._1, (), x._2)
  implicit def tuple3EA[A, B](x: (A, B, A)): EA[A, B] = Edge(x._1, x._2, x._3)
  implicit def node2EA[A](x: Node[Node[A]]): EA[A, Unit] = x.a
  implicit def any2EA[A](x: A): EA[A, Unit] = Node(x)

  def apply[A, B](edges: EA[A, B]*): Graph2[A, B] = {
    var v = Set[A]()
    var e = Map[A, Map[A, B]]()
    
    def addEdge(x: Edge[A, B]) = {
      v = v + x.a
      v = v + x.b
      
      if (e.contains(x.a)) {
        if (e(x.a).contains(x.b)) {
          throw new RuntimeException("Already have an edge from " + x.a + " to " + x.b)
        }
        else {
          e = e + ((x.a, Map[A, B]((x.b, x.c))))
        }
      }
      else {
        e = e + ((x.a, Map[A, B]((x.b, x.c))))
      }
    }
    
    def addNode(x: Node[A]) {
      v = v + x.a
    }
    
    for (x <- edges) {
      x match {
        case e@Edge(a, c, b) => addEdge(e)
        case Node(n@Node(a)) => addNode(n)
        case n@Node(a) => addNode(n)
      }
    }
    
    return new Graph2(v, e)
  }

}
                                                         
class Graph2[A, +B](v: Set[A], e: Map[A, Map[A, B]]) {
  
  override def toString = "V = { " + v.mkString(", ") + " }\nE = {" + edgeStringList + " }"
  
  def edgeStringList = {
    val el = for (x <- e; y <- x._2) yield (x._1, y._2, y._1)
    val esl = el.map { x => x._2 match { case () => x._1 + " -> " + x._3 ; case _ => x._1 + " -- " + x._2 + " -> " + x._3 } }
    esl.mkString(", ")
  }
  
}
