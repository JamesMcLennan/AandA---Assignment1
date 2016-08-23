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
	static int exists = -1;
	
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
    	/*else {
    		throw new IllegalArgumentException("[!] IA Exception *Add Edge* - Node does not exist");
    	}*/
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
       /* else {
    		throw new IllegalArgumentException("[!] IA Exception *Neighbours* - Node does not exist");
        }*/
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
    	
    	/*if(found == false) {
    		throw new IllegalArgumentException("[!] IA Exception *RV* - Node does not exist");
    	}*/
    } // end of removeVertex()
	
    public void removeEdge(T srcLabel, T tarLabel) {
    	node source = null, target = null;
    	int srcPos = exists, tarPos = exists;
    	
    	srcPos = findVertex(LinkedArray, srcLabel);
    	tarPos = findVertex(LinkedArray, tarLabel);
    	
    	if(srcPos != exists && tarPos != exists) {
    		source = LinkedArray[srcPos];
    		target = LinkedArray[tarPos];
    		
    		source.deleteNodeInList(source, tarLabel);
    		target.deleteNodeInList(target, srcLabel);
    	}
    	/*else if(srcPos == exists || tarPos == exists) {
    		throw new IllegalArgumentException("[!] IA Exception *RE* - Node does not exist");
    	}*/
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
    
    @SuppressWarnings("unchecked")
	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	int srcPos = -1, tarPos = -1, prevPos = 0;
    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].visited != false) {
    			LinkedArray[i].visited = false;
    		}
    	}
    	
    	srcPos = findVertex(LinkedArray, vertLabel1);
    	tarPos = findVertex(LinkedArray, vertLabel2);
    	if(srcPos != -1 && tarPos != -1) {
    		node peek = LinkedArray[srcPos];
    		peek.parentNode = null;
    		peek.pos = 0;
    		ArrayList<T> neighbours = neighbours((T) peek.vertex);
	    	Queue<node> queue = new LinkedList<node>();
	    	int found = exists;
	    	int path = 0;
	    	
	    	
	    	while(neighbours != null) {
	    		neighbours = neighbours((T) peek.vertex);
	    		if(neighbours.contains(vertLabel2)) {
	    			LinkedArray[tarPos].pos = prevPos + 1;
	    			return LinkedArray[tarPos].pos;
		    	}
	    		
	    		else {
	    			LinkedArray[srcPos].visited = true;
	    			for(int i = 0; i < neighbours.size(); i++) {
		    			found = findVertex(LinkedArray, neighbours.get(i));
		    			if(LinkedArray[found].visited != true) {
		    				queue.add(LinkedArray[found]);
		    				LinkedArray[found].parentNode = peek;
		    				LinkedArray[found].pos = LinkedArray[found].parentNode.pos + 1;
		   					prevPos = LinkedArray[found].pos;
		   					LinkedArray[found].visited = true;
		   				}
		    			
		    		}
	    			
	    			if(path != 0) {
		    			queue.remove();
		    		}
		    	}
	    		path++;
	    		peek = queue.peek();
	    		if(queue.isEmpty()) {
	    			break;
	    		}
	    	}
	    }
    	// if we reach this point, source and target are disconnected
        return disconnectedDist; 
    }
} // end of class AdjList