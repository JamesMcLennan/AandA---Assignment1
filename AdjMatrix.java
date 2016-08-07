import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	/**
	 * Contructs empty graph.
	 */
    ArrayList<T> verlabels;
    protected boolean[][] adjmatrix;

    public AdjMatrix() {

	// Implementing arrays for storing data (For now kept as 10).         
	verlabels = new ArrayList<T>();
	adjmatrix = new boolean[10][10];

	
	// Setting the arrays to default values.
	for(int i = 0; i < 10; i++){

		verlabels.add(null);

		for(int k = 0; k < adjmatrix.length; k++){


			adjmatrix[i][k] = false;

		}

	}
	
	// Printing for debug use.
	System.out.println("[!] AdjMaxtrix initialised.");


    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
	
	
	// Checking for available space and adding it to the verlables;      
	for(int i = 0; i < 10; i++){

		if(verlabels.get(i) == (null)){
			
			verlabels.set(i, vertLabel);

			// For debug purposes.
			System.out.println("[!] Vertex Added!");

			break;
		}

		if(i == verlabels.size()){
		
			// For debug purposes.
			System.out.println("[!] Could not add vertex!");
	
		}
	}

    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {

	// Variables for use;
	int src_location = -1;
	int tar_location = -1;

	// Checking the srcLabel location with a for loop;
	for(int i = 0; i < verlabels.size(); i++){

		try{
			if(verlabels.get(i).equals(srcLabel)){
			
				src_location = i;
			
				// For debug purposes.
				System.out.println("[!] Found srcLabel at: " + i + ".");	
			} 

			if(verlabels.get(i).equals(tarLabel)){
			
				tar_location = i;
			
				// For debug purposes.
				System.out.println("[!] Found tarLabel at: " + i + ".");	
			} 		



		}catch (NullPointerException e){

			// Do nothing;
		}
	

	}


	if((src_location == -1) || (tar_location == -1)){

		System.out.println("[!] Not a valid edge. ");
		
	}else {


		adjmatrix[src_location][tar_location] = true;
		adjmatrix[tar_location][src_location] = true;

		// For debug purposes.
		System.out.println("[!] AdjMatrix updated.");

	}

    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();

	// Variables for use;
	int location = findvertex(vertLabel);

	if(location == -1){

		System.out.println("[!] Edge does not exist.");
		
	}else {
		
		for(int i = 0; i < verlabels.size(); i++){

			if(adjmatrix[i][location] == true){

				neighbours.add(verlabels.get(i));
			
			}

		} 

	}
  
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {


	// Variables for use;
	int location = findvertex(vertLabel);

	if(location == -1){

		System.out.println("[!] Vertex does not exist.");
		
	}else {
		
		// Moving the vertlabels to -1 position;
		while(location < verlabels.size()){

			if(location != verlabels.size() - 1){
			
				verlabels.set(location, verlabels.get(location + 1));
			}

			location++;

		} 
		
		// Setting the last variable in the array to null;
		verlabels.set(verlabels.size() - 1, null);

		// For debug purposes;
		System.out.println("[!] Verlabels array reshuffled.");

		// Shuffling (horizontal) the boolean matrix to -1;
		while(location < 10){

			for(int i = location; i < 10; i++){

				if(i == adjmatrix.length - 1){

					adjmatrix[location][i] = false;

				}
	
				adjmatrix[location][i] = adjmatrix[location + 1][i + 1];

			}

			location++;

		}
		
	}

	

     } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {

	int j = 0;
	
	// Printing the verlabels.
	while(verlabels.get(j) != null){

 
		System.out.print("  " + verlabels.get(j)+ "   ");

		j++;

	}

	System.out.println(" ");

	for(int i = 0; i < j; i++){

		for(int l = 0; l < j; l++){

			System.out.print("------");

		}	

		System.out.println("");

		for(int k = 0; k < j; k++){
		
			if(adjmatrix[i][k] == false){
				
				System.out.print("  0  |");

			}else {

				System.out.print("  1  |");

			}


		}

		System.out.println("");

	}

	for(int l = 0; l < j; l++){

			System.out.print("------");

	}

	System.out.println("");

    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()


    public int findvertex(T vertex){

	// Variable for use;
	int location = -1;

	   
	// Searching for the column/row location of the vertLabel;
	// Checking the Label location with a for loop;
	for(int i = 0; i < verlabels.size(); i++){

		try{
			if(verlabels.get(i).equals(vertex)){
			
				// For debug purposes.
				System.out.println("[!] Found Label at: " + i + ".");

				location = i;
				break;					
				
			} 


		}catch (NullPointerException e){

			// Do nothing;
		}
	

	}

	return location;
	
    }
    
} // end of class AdjMatrix