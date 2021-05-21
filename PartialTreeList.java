package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Arc;
import structures.Graph;
import structures.MinHeap;
import structures.PartialTree;
import structures.Vertex;

/**
 * Stores partial trees in a circular linked list
 * 
 */
public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */
		
		PartialTreeList list = new PartialTreeList();
		Vertex vertices[] = graph.vertices;

		for( Vertex vtx : vertices ) {
			
			PartialTree pt = new PartialTree( vtx );
			MinHeap<Arc> arcs = pt.getArcs() ;
			
			
			while( vtx.neighbors != null ) {
				Arc narc = new Arc( vtx, vtx.neighbors.vertex, vtx.neighbors.weight ) ;
				arcs.insert( narc ) ;
				vtx.neighbors = vtx.neighbors.next ;
			}
			list.append( pt ) ;
		}
		
		return list ;
	}
	
	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * for that graph
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	
	public static ArrayList<Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */

		ArrayList<Arc> mst = new ArrayList<Arc>() ;
		while (ptlist.size() > 1) {
			
			PartialTree pt1 = ptlist.remove() ;		
			Vertex root = pt1.getRoot();
			MinHeap<Arc> arcs = pt1.getArcs() ;

			
			while ( arcs.size() > 0 ) {
				
				Arc narc = arcs.deleteMin() ;
				
				if( narc.getv2().getRoot() == root ) continue ;
				
				PartialTree pt2 = ptlist.removeTreeContaining( narc.getv2() ) ;
				
				if( pt2 != null ) {
					mst.add( narc ) ;
					pt1.merge( pt2 ) ;
					break ;
				}
			}
			
			ptlist.append( pt1 ) ;
			
		}
		return mst;
		
	}
	
    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    			
    	if (rear == null) {
    		throw new NoSuchElementException("list is empty");
    	}
    	PartialTree ret = rear.next.tree;
    	if (rear.next == rear) {
    		rear = null;
    	} else {
    		rear.next = rear.next.next;
    	}
    	size--;
    	return ret;
    		
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    	/* COMPLETE THIS METHOD */
    	
    	if( size == 0 ) {
    		throw new NoSuchElementException( "Tree Not Found" ) ;
    	}
    	
    	Vertex root = vertex.getRoot() ;
    	Node prev = rear ;
    	
    	
    	for( int i = 1; i <= size; i++ ) {
    		Node nd = prev.next ;
    		PartialTree pt = nd.tree ;
    		
    		
    		if( pt.getRoot() == root ) {
    			if( size == 1 ) {
    				rear = null ;
    				size = 0 ;
    			}else {
    				prev.next = nd.next ;
    				if( nd == rear ) {
    					rear = prev ;
    				}
    				size-- ;
    			}
    			return pt ;
    		}
    		prev = nd ;
    	}
    	throw new NoSuchElementException( "Tree Not Found" ) ;
    	
     }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


