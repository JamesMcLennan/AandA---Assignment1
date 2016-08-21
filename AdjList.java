import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */

public class AdjList <T extends Object> implements FriendshipGraph<T>
{  
	static int initial = 5;
	static int arrayListSize = 0;
	static int arrayQueueSize = 0;
	static int pos = 0;
	
	private static node[] LinkedArray = new node[initial];
	
	public AdjList() {
    	for(int i = 0; i < LinkedArray.length; i++) {
    		LinkedArray[i] = new node(null);
    	}
    } // end of AdjList()
    
	public node[] resizeArray(node[] LinkedArray, Object lastVertex) {
		node[] tempArray = new node[initial];
		int lastNodePos = 0;
		
		if(arrayListSize == initial){
			for (int j = 0; j < LinkedArray.length; j++) {
				tempArray[j] = LinkedArray[j];
			}
			initial = initial * 2;
			LinkedArray = new node[initial];
			for (int m = 0; m < tempArray.length; m++) {
				LinkedArray[m] = tempArray[m];
				lastNodePos = m + 1;
			}
			for(int n = lastNodePos; n < LinkedArray.length; n++) {
				LinkedArray[n] = new node(null);
			}
		}
		LinkedArray[lastNodePos] = new node(lastVertex);
		arrayListSize++;
		return LinkedArray;
	}
	
	public int findVertex(node[] node, Object vertex) {
		int position = -1;
		
		for(int i = 0; i < node.length; i++) {
    		if(node[i].vertex != null) {
	    		if(node[i].vertex.equals(vertex)) {
	    			return i;
	    		}
	    	}
	    }
		return position;
	}
	
	public void addVertex(T vertLabel) {
    	boolean exists = false;
    	int pos;
    	
    	pos = findVertex(LinkedArray, vertLabel);
    	if(pos != -1) {
    		exists = true;
    	}
    	
    	if(exists == false && arrayListSize != initial) {
	    	for(int i = 0; i < LinkedArray.length; i++) {
	    		if(LinkedArray[i].vertex == null) {
	   				node newNode = new node(vertLabel);
	   				LinkedArray[i] = newNode;
	   				arrayListSize++;
	   				break;
	   			}
    		}	
	    }
	    
    	else if (exists == false && arrayListSize == initial){
	   		LinkedArray = resizeArray(LinkedArray, vertLabel);
	    }
	} // end of addVertex()
	
	public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    	node source = null, target = null;
    	boolean srcFound = false, tarFound = false, exists = false;
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
    		node find = LinkedArray[i];
    		if(find.vertex != null) {
    			if(find.vertex.equals(srcLabel)) {
    				source = find;
	    			while(find.vertex != null) {
	    				if(find.nextNode != null) {
	    					if(find.nextNode.vertex.equals(tarLabel)) {
	    						exists = true;
	    						break;
	    					}
	    					
	    					else {
	    						find = find.nextNode;
	    					}
	    				}
	    				
	    				else {
	    					break;
	    				}
	    			}
	    		}
    		}
    	}
    	
    	if(exists != true) {
	    	for(int i = 0; i < LinkedArray.length; i++) {
	    		node array = LinkedArray[i];
	    		if(array.vertex != null) {
	    			if(array.vertex.equals(srcLabel)) {
		    			source = array;
		    			srcFound = true;
		    		}
		    		if(array.vertex.equals(tarLabel)) {
		    			target = array;
		    			tarFound = true;
		    		}
	    		}
	    	}
	    	
	    	if(srcFound == true && tarFound == true) {
	    		Object temp = null;
		   		node srcLast = null, tarLast = null;
		    		
		   		if(source.nextNode == null) {
		   			temp = target.vertex;
		   			node addEdge = new node(temp);
		   			source.nextNode = addEdge;
		   		}
	    		
	    		else {
		    		srcLast = source;
		   			temp = target.vertex;
		   			while(srcLast.nextNode != null) {
		   				srcLast = srcLast.nextNode;
		   			}
		   			node addEdge = new node(temp);
		   			srcLast.nextNode = addEdge;
		   		}
		    		
		   		if(target.nextNode == null) {
		    		temp = source.vertex;
		   			node addEdge = new node(temp);
		   			target.nextNode = addEdge;
		   		}
		    		
		    	else {
		    		tarLast = target;
		   			temp = source.vertex;
		   			while(tarLast.nextNode != null) {
		   				tarLast = tarLast.nextNode;
		   			}
	    			node addEdge = new node(temp);
		    		tarLast.nextNode = addEdge;
		    		
		    	}
		    }
	    	else {
	   			System.err.println("Error - Please enter a valid Vertex");
	   		}
    	}
   	} // end of addEdge()
	
    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        node temp;
        int pos;
        
        pos = findVertex(LinkedArray, vertLabel);
        if(pos != -1) {
        	temp = LinkedArray[pos];
        	while(temp.nextNode != null) {
				neighbours.add((T) temp.nextNode.vertex);
				temp = temp.nextNode;
			}
        }
        return neighbours;
    } // end of neighbours()
    
    public void removeVertex(T vertLabel) {
        node temp = null;
        boolean found = false;
    	for(int i = 0; i < LinkedArray.length; i++) {
        	temp = LinkedArray[i];
        	if(temp.vertex != null) {
        		while(temp.vertex != null) {
        			if(temp.vertex.equals(vertLabel)) {
        				temp.vertex = null;
        				found = true;
        				arrayListSize--;
        			}			
        			else {
        				if(temp.nextNode != null) {
        					temp.deleteNodeInList(temp, vertLabel);
        				}
        				break;
        			}
        		}
        	}
        	if(found == true) {
	        	if(temp.nextNode != null) {
		       		temp.deleteNodeInList(temp, vertLabel);
		       	}
        	}
        }
    	
    	if(found == false) {
    		System.err.println("Error - Please enter a valid Vertex");
    	}
    } // end of removeVertex()
	
    public void removeEdge(T srcLabel, T tarLabel) {
    	node source = null, target = null;
    	boolean srcFound = false, tarFound = false;
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
        	node array = LinkedArray[i];
        	if(array.vertex != null) {
        		if(array.vertex.equals(srcLabel)) {
        			source = array;
	        		srcFound = true;
	        	}
	        	if(array.vertex.equals(tarLabel)) {
	        		target = array;
	        		tarFound = true;
	        	}
        	}
        }
    	
    	if(srcFound == true && tarFound == true) {
    		source.deleteNodeInList(source, tarLabel);
    		target.deleteNodeInList(target, srcLabel);
    	}
    } // end of removeEdges()
	
    public void printVertices(PrintWriter os) {
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex != null) {
    			os.print(LinkedArray[i].vertex + " ");
    		}
    	}	
    	os.println("");
    } // end of printVertices()
	
    public void printEdges(PrintWriter os) {
        for(int i = 0; i < LinkedArray.length; i++) {
        	if(LinkedArray[i].vertex != null) {
        		if(LinkedArray[i].nextNode != null) {
        			LinkedArray[i].printEdge(LinkedArray[i], os);
        		}
        	}
        }
    } // end of printEdges()
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	int srcPos = -1, tarPos = -1, queueCount = 0;
    	Object[] queue = new Object[initial];
    	node vertex;
    	boolean emptyQ = false;
    	
    	arrayQueueSize = 0;
    	for(int i = 0; i < queue.length; i++) { //Initialize the queue
    		queue[i] = null;
    	}
    	
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].visited != false) {
    			LinkedArray[i].visited = false;
    		}
    	}
    	
    	srcPos = findVertex(LinkedArray, vertLabel1); //Find source
    	tarPos = findVertex(LinkedArray, vertLabel2); //Find target
    	
    	if(tarPos != -1 && tarPos != -1)  { //if both target and source exist
    		queue[queueCount] = LinkedArray[srcPos].vertex; //Insert source node into the queue
    		LinkedArray[srcPos].pos = 0; //Root node pos set.
    		LinkedArray[srcPos].visited = true;
    		
    		while(emptyQ != true) {
    			if(queue[queueCount] == null) { //Check if queue no longer has items to check
    	    		break; 
    	    	}
    			    			
    			if(queueCount != 0) { //Find next element (That is not the first input).
    	    		srcPos = findVertex(LinkedArray, queue[queueCount]); //Find new source
    	    	}
    			
    			vertex = LinkedArray[srcPos];
    			
    			//System.out.println("Searching for: " + vertex.vertex);
    	    	if(queue[queueCount].equals(vertLabel2)) { //Check if node matches target
    				return vertex.pos;
    			}
    			
    			else {
    				if(vertex.nextNode != null) {
    					queue = addNodeQueue(queue, vertex); //Add source child nodes into queue
    					queueCount++;
    				}
    				else {
    					queueCount++; //Move to next element in queue
    				}
    			}
    		}
    	}
    	
    	// if we reach this point, source and target are disconnected
        return disconnectedDist; 
    }
    
  public Object[] addNodeQueue(Object[] queue, node head) {
    	boolean checkQueue = false;
    	node parent = head;
    	
    	while(head.nextNode != null) {
	    	for(int i = 0; i < queue.length; i++) {
	    		if(queue[i] == null) { //Move to next null position in Queue
	    			
	    			checkQueue = checkQueue(LinkedArray, head.nextNode.vertex); //Check if the child has already been added into the queue
	    			
	    			if(checkQueue != true && arrayQueueSize != initial) {
	    				
	    				queue[i] = head.nextNode.vertex; //Add child into queue
	    				head.nextNode.parentNode = parent; //Set child's parent
	    				
	    				head.nextNode.visited = true;
	    				setVisited(LinkedArray, head.nextNode);
	    				
	    				head.nextNode.pos = head.nextNode.parentNode.pos + 1; //Set the position of the child's
		    			setDistance(LinkedArray, head.nextNode); //Find child in LinkedArray.
		    			
		    			arrayQueueSize++;
		    			break;
		    			
	    			}
	    			
	    			else if(checkQueue != true && arrayQueueSize == initial) { //***IF I GET TIME - RESEARCH AND IMPLEMENT ACTUAL QUEUE***
	    				queue = resizeQueue(queue, head.nextNode.vertex); //Queue needs to be resized.
	    			}
	   			}
    		}
	    	head = head.nextNode;
    	}
    	return queue;
    }
    
    public boolean checkQueue(node[] LinkedArray, Object vertex) {
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex.equals(vertex)) {
    			//System.out.println("[!] " + LinkedArray[i].vertex + " matches node suggested for Queue: " + vertex);
    			if(LinkedArray[i].visited == true) {
        			//System.out.println("[!] " + LinkedArray[i].vertex + " has been visited");
    				return true;
    			}
    			else {
        			//System.out.println("[!] " + LinkedArray[i].vertex + " has NOT been visited");
    				return false;
    			}
    		}
    	}
    	return false;
    }
    
    public Object[] resizeQueue(Object[] queue, Object node) {
    	Object[] tempQueue = new Object[initial];
		int lastNodePos = 0;
		
		if(arrayQueueSize == initial){
			for (int j = 0; j < LinkedArray.length; j++) {
				tempQueue[j] = queue[j];
			}
			initial = initial * 2;
			queue = new Object[initial];
			for (int m = 0; m < tempQueue.length; m++) {
				queue[m] = tempQueue[m];
				lastNodePos = m + 1;
			}
			for(int n = lastNodePos; n < queue.length; n++) {
				queue[n] = null;
			}
		}
		queue[lastNodePos] = node;
		arrayQueueSize++;
		return queue;
    }
    
    public void setDistance(node[] LinkedArray, node head) {
		for(int i = 0; i < LinkedArray.length; i++) {
			if(LinkedArray[i] != null) {
				if(LinkedArray[i].vertex.equals(head.vertex)) {
					LinkedArray[i].pos = head.pos;
					break;
				}
			}
		}
	}
    
    public void setVisited(node[] LinkedArray, node head) {
    	for(int i = 0; i < LinkedArray.length; i++) {
			if(LinkedArray[i] != null) {
				if(LinkedArray[i].vertex.equals(head.vertex)) {
					LinkedArray[i].visited = head.visited;
					break;
				}
			}
		}
    }
        	
} // end of class AdjList