import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */

class node{
	public Object vertex;
	public node nextNode;
	public node prevNode;
	
	//Constructor for new nodes with T input.
	public node(Object vertInput) {
		vertex = vertInput;
		nextNode = null;
		prevNode = null;
	}
	
	//Testing print for new vertices added
	public void print() {
		if(vertex != null) {
			System.out.print(vertex + " ");
		}
	}
	
	public void printList(node head) {
		while(head.vertex != null) {
			System.out.print(head.vertex + " ");
			if(head.nextNode != null) {
				head = head.nextNode;
			}
			else {
				System.out.println("");
				break;
			}
		}
	}
}

public class AdjList <T extends Object> implements FriendshipGraph<T>
{  
	private static node[] LinkedArray = new node[10];
	
    public AdjList() {
    	for(int i = 0; i < LinkedArray.length; i++) {
    		LinkedArray[i] = new node(null);
    	}
    } // end of AdjList()
    
    public void addVertex(T vertLabel) {
    	node newNode = new node(vertLabel);
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex == null) {
    			LinkedArray[i] = newNode; 
		    	break;
		    }
    	}
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    	node source = null, target = null;
    	boolean srcFound = false, tarFound = false;
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
    		node array = LinkedArray[i];
    		if(array.vertex != null) {
    			if(array.vertex.equals(srcLabel)) {
	    			System.out.println("[!] Source found: " + array.vertex);
	    			source = array;
	    			srcFound = true;
	    		}
	    		if(array.vertex.equals(tarLabel)) {
	    			System.out.println("[!] Target found: " + array.vertex);
	    			target = array;
	    			tarFound = true;
	    		}
    		}
    	}
    	
    	if(srcFound == true && tarFound == true) {
    		node srcLast = null, tarLast = null;
    		
    		if(source.nextNode == null) {
    			source.nextNode = target;
    		}
    		else {
    			srcLast = source;
    			while (srcLast.nextNode != null) {
    				srcLast = srcLast.nextNode;
    			}
    			srcLast.nextNode = target;
    		}
    		
    		if(target.nextNode == null) {
    			target.nextNode = source;
    		}
    		else {
    			tarLast = target;
    			while (tarLast.nextNode != null) {
    				tarLast = tarLast.nextNode;
    			}
    			tarLast.nextNode = source;
    		}
    	}
    	else {
    		System.out.println("[!] Nup");
    	}
    	
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        for(int i = 0; i < LinkedArray.length; i++) {
        	if(LinkedArray[i].vertex != null) {
        		if(LinkedArray[i].vertex.equals(vertLabel)) {
        			LinkedArray[i].vertex = null;
        		}
        	}
        }
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    
	public void printVertices(PrintWriter os) {
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex != null) {
    			System.out.print(LinkedArray[i].vertex + " ");
    		}
    	}	
    	System.out.println("");
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        for(int i = 0; i < LinkedArray.length; i++) {
        	if(LinkedArray[i].vertex != null) {
        		if(LinkedArray[i].nextNode != null) {
        			//Implement loop to print nodes.
        		}
        	}
        }
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
        	
} // end of class AdjList