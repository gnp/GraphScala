# README

GraphScala contains code you can use to construct graphs.

I have used this to validate what people told me about what was *supposedly* in the
data set they were going to have me depend on. I would get the data file from them
and run this and then ask them all the follow up questions like "wait, what does
it mean when the order_date field contains the text 'X' instead of a date?".

Here's a simple example code fragment from one of the unit tests. It sets up an
input source to read from a tab-separated values text stream and outputs the assay
results to the console:

    import org.exoprax.graph.Graph2._
    
    val triple = 1 -- 2 -> 3 // Represents an edge from Node 1 to Node 3 with annotation 2
    val g1 = Graph2(1 -> 2, 2 -> 3, 3 -> 1) // Cycle from Node 1 to Node 2 to Node 3 and back to Node 1
    val g2 = Graph2(1 -> 2 -> 3) // Graph with two nodes: the Tuple (1, 2) and the Number 3. One edge from (1, 2) to 3
    val g3 = Graph2(1 -- 2 -> 3) // Graph with two Nodes: 1 and 3, and an edge from Node 1 to Node 3 with annotation 2
    val g4 = Graph2("A" -> 2) // Graph with two Nodes: String "A" and Integer 2
    val g5 = Graph2("Z")  // Graph with one Node and no Edges
    val g6 = Graph2("Z", "A" -> 2, 42) Graph with four Nodes: "Z", "A", 2 and 42 and one Edge (from "A" to 2)
    val g7 = Graph2("A", "A" -> "C", "B", "B" -- 14 -> "D")
