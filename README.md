# CS112-Project-5-Mini-Tree

Summary:

You need to write your own driver program to create a Graph object from an input graph file, and call the MST methods to run the algorithm on this graph.

For all the test graphs you use, create files with the same format as the given graph1.txt and graph2.txt files, and place them in the same folder as those.

Note: The PartialTree class toString method returns a string that shows a single (root) vertex and a list of arcs in the priority queue. This is different from the illustration of the algorithm. So, for instance, the illustration had this after the first merge of step 8, for the tree T13:

Vertices:AC PQ:(CA1),(CE2),(AD3),(AB4),(CD4),(CB5)

But the PartialTree class toString method will show this:

Vertices:A PQ: (CA1) (CE2) (AD3) (AB4) (CB5) (CD4)

(The A vertex is shown because it is the root. The C vertex is not shown.)

MORE INFO: Assignment 5 Mini Tree.pdf
