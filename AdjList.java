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
	public int size;
	
	//Constructor for new nodes with T input.
	public node(Object vertInput) {
		vertex = vertInput;
		nextNode = null;
		prevNode = null;
	}
	
	public Object getVertex() {
		return vertex;
	}
	
	public node findVertex(node head, Object data) {
		node temp = null;
		while(head != null) {
			if(head.vertex.equals(data)) {
				return head;
			}
			if(head.nextNode == null) {
				head = null;
			}
			else {
				head = head.nextNode;
			}
		}
		return temp;
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
    			LinkedArray[i].size++; //Temp size of list
		    	newNode.print(); //Testing print.
		    	break;
		    }
    	}
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    	Object copy = null;
    	node search = null, source = null, target = null;
    	boolean srcFound = false, tarFound = false;
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
    		node array = LinkedArray[i];
    		if(array.vertex != null) {
    			search = array.findVertex(array, srcLabel);
    			if(search != null) {
    				System.out.println("[!] Source found: " + search.vertex);
    				source = search;
    				srcFound = true;
    			}
    		}
    		if(array.vertex != null) {
    			search = array.findVertex(array, tarLabel);
    			if(search != null) {
    				System.out.println("[!] Target found: " + search.vertex);
    				target = search;
    				tarFound = true;
    			}
    		}
    	}
    	
    	if(srcFound == true && tarFound == true) {
    		copy = target.getVertex();
    		node newTarget = new node(copy);
    		source.nextNode = newTarget;
    		target.vertex = null;
    	}
    	
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    
	public void printVertices(PrintWriter os) {
    	//node temp;
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex != null) {
    			LinkedArray[i].printList(LinkedArray[i]);
    		}
    	}	
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
        	
} // end of class AdjList