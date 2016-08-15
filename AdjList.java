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
	public Object vertex; //Data of Node (Vertex)
	public node nextNode; // Pointer to next Node
	
	//Constructor for new nodes with T input.
	public node(Object vertInput) {
		vertex = vertInput; //Create node with vertInput
		nextNode = null; //Initial nextNode is null
	}
	
	//Print edges in list - NEED TO CHANGE OS.PRINT
	public void printEdge(node list, PrintWriter os) {
		node head = list; 
		while(list.vertex != null) {
			if(list.nextNode != null) {
				os.println(head.vertex + " " + list.nextNode.vertex + " ");
				list = list.nextNode;
			}
			
			else {
				break;
			}
		}
	}
	
	public void deleteNodeInList(node delTarNode, Object vertexTar) {
		while(delTarNode.vertex != null) {
			if(delTarNode.nextNode != null) {
				if(delTarNode.nextNode.vertex.equals(vertexTar)) {
					delTarNode.nextNode.vertex = null;
					
					if(delTarNode.nextNode.nextNode != null) {
						delTarNode.nextNode = delTarNode.nextNode.nextNode;
					}
					
					else {
						delTarNode.nextNode = null;
					}
				}
				
				else {
					delTarNode = delTarNode.nextNode;
				}
			}
			
			else {
				break;
			}
		}
	}
	
	public void findNodeInList(node head, Object vertexTar) {
		while(head.vertex != null) {
			if(head.nextNode != null) {
				if(head.nextNode.vertex.equals(vertexTar)) {
					head.nextNode.vertex = null;
					
					if(head.nextNode.nextNode != null) {
						head.nextNode = head.nextNode.nextNode;
					}
					
					else {
						head.nextNode = null;
					}
				}
				
				else {
					head = head.nextNode;
				}
			}
			
			else {
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
    	boolean exists = false;

    	for(int i = 0; i < LinkedArray.length; i++) {
    		if(LinkedArray[i].vertex != null) {
    			if(LinkedArray[i].vertex.equals(vertLabel)) {
    				exists = true;
    				break;
    			}
    		}
    	}
    	
    	if(exists == false) {
    		for(int i = 0; i < LinkedArray.length; i++) {
    			if(LinkedArray[i].vertex == null) {
    				node newNode = new node(vertLabel);
    				LinkedArray[i] = newNode;
    				break;
    			}
    		}
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
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
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
        			}			
        			else {
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
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
        	
} // end of class AdjList