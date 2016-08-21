import java.io.*;

public class node{
	
	public Object vertex; //Data of Node (Vertex)
	public node nextNode; // Pointer to next Node
	public node parentNode;
	public int pos;
	boolean visited;
	
	//Constructor for new nodes with T input.
	public node(Object vertInput) {
		vertex = vertInput; //Create node with vertInput
		nextNode = null; //Initial nextNode is null
		parentNode = null;
		pos = -1;
		visited = false;
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