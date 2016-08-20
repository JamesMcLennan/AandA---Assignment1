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

    // Arrays;
    protected ArrayList<T> verlabels;
    protected boolean[][] adjmatrix;

    // Variables;
    int moves = 0;
    int shortest;

    public AdjMatrix() {

	// Implementing arrays for storing data (Starting off at 1).         
	adjmatrix = new boolean[1][1];
	verlabels = new ArrayList<T>();
	
	adjmatrix[0][0] = false;
	
	// Printing for debug use.
	System.out.println("[!] AdjMaxtrix initialised.");


    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {

		// Checking if variable exists;
		if(findvertex(vertLabel) != -1){
	
			//System.out.println("[!] Vertex already exists, please use a different value.");

		}else {

			verlabels.add(vertLabel);

			// Checking if the size of the matrix has changed;
			if(adjmatrix.length < verlabels.size()){

				// Calling the method to increase the adjmatrix.
				adjmatrix = resizeArray(adjmatrix);		
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
			} 

			if(verlabels.get(i).equals(tarLabel)){
			
				tar_location = i;
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
	}

    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();

	// Variables for use;
	int location = findvertex(vertLabel);

	if(location == -1){

		System.out.println("[!] Vertex does not exist.");
		
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
	int temp = location;
	
	// Checking if the vertex actually exists.
	// findvertex() returns -1 if it doesn't.
	if(location == -1){

		System.out.println("[!] Vertex does not exist.");
		
	}else {
		
		// Moving the vertlabels to -1 position;
		while(temp < verlabels.size()){

			if(temp != verlabels.size() - 1){
			
				verlabels.set(temp, verlabels.get(temp + 1));
			}

			temp++;
		} 
		
		// Deleting the last array element;
		verlabels.remove(verlabels.size() - 1);
		
		// Reseting the values;
		temp = location;

		// Shuffling the boolean matrix (horizontally) to -1;
		while(temp < 10){

			for(int i = 0; i < 10; i++){

				try{
					if(i == adjmatrix.length - 1){
	
						adjmatrix[i][temp] = false;

					}else {

						adjmatrix[i][temp] = adjmatrix[i][temp + 1];
					}

				}catch(ArrayIndexOutOfBoundsException e){
					
					// Do nothing;
				}
			}

			temp++;
		}

		temp = location;
	
		// Shuffling the boolean matrix vertically;
		while(temp < 10){

			for(int i = 0; i < 10; i++){

				try{
					if(i == adjmatrix.length - 1){
	
						adjmatrix[temp][i] = false;

					}else {

						adjmatrix[temp][i] = adjmatrix[temp + 1][i];
					
					}

				}catch(ArrayIndexOutOfBoundsException e){
					
					// Do nothing;
				}
			}

			temp++;
		}

		// Checking if the size of the matrix has changed;
		if(adjmatrix.length > verlabels.size()){
		
			// Calling the method to increase the adjmatrix.
			adjmatrix = resizeArray(adjmatrix);		
		}
				
	}

     } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
	// Variables for use;
	int src_location = findvertex(srcLabel);
	int tar_location = findvertex(tarLabel);

	// Checking if the values are -1
	if((src_location == -1) || (tar_location == -1)){

		System.out.println("[!] Edge does not exist.");

	}else {

		// Checking if they are currently not an Edge;	
		// Checking only the column and row as the graph
		// is symtrical;
		if((adjmatrix[src_location][tar_location] == false)){
			
			System.out.println("[!] Edge does not exist.");

		}else {

			adjmatrix[src_location][tar_location] = false;
			adjmatrix[tar_location][src_location] = false;
		}
	}


    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
		
	// Printing the verlabels.
	for(int i = 0; i < verlabels.size(); i++){

		os.print(verlabels.get(i)+ " ");
	}
	
	os.println("");

	os.flush();


    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        
	// Checking the boolean adjmatrix, if true prints out the edges.
	for(int i = 0; i < verlabels.size(); i++){

		for(int k = 0; k < verlabels.size(); k++){

			if(adjmatrix[i][k] == true){
			
				os.println(verlabels.get(i) + " " + verlabels.get(k));
			}
		}
	}
	
	os.flush();
       

    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	
	// Variables for use;
	int vert1_location = findvertex(vertLabel1);
	int vert2_location = findvertex(vertLabel2);

        // value for shortest;
        shortest = verlabels.size();

	// Array for storing previous;
	int[] previous = new int[verlabels.size()];

	// Initialising for the recusive loop.
	for(int i = 0; i < previous.length; i++){
		previous[i] = -1;
	}

	previous[0] = vert1_location;

	// Array for storing edges.
	int distance = findedges(vert1_location, vert2_location, previous, moves);
				
        // if we reach this point, source and target are disconnected
        return distance;    	
    } // end of shortestPathDistance()


    // Recursive loop;
    public int findedges(int source, int target, int previous[], int counter){
	
	// Array 
	int[] edges = new int[verlabels.size()];

	// Array counter;
	int array_counter = 0;
	int distances[];
        int min = verlabels.size(); 
	
	// Checking if counter == verlabels.size;
        // If it is it means its reach the max distance possible;
        // No bother continuing down the tree path;
        if((counter == verlabels.size()) || (counter > shortest)){

		return shortest;

	}

	// Finding all edges of the source in the matrix;
	for(int k = 0; k < verlabels.size(); k++){

		if(adjmatrix[source][k] == true){

			// Checking if its in the previous;
			boolean found = false;
			for(int i = 0; i < previous.length; i++){
				if(previous[i] == k){
					found = true;
				}
			}
			if(found == false)
			{
				edges[array_counter] = k;
				array_counter++;	
			}

		}
	}

	// Adding a previous to the node;
	for(int l = 0; l < previous.length; l++){

		if(previous[l] == -1){
			
			previous[l] = source;
			break;
		}

	} 

	// Checking if the array has the target;	
	for(int k = 0; k < array_counter; k++){

		if(edges[k] == target){

			// Checking if its shorter then current shortest;
                	if(counter < shortest){

				shortest = counter + 1;

                        	System.out.println("Short: " + shortest);
   
                                return shortest;		
			}
		
		}
	}
	
        // Creating distances;
        distances = new int[array_counter];

	// Testing if the array has the target;	
	for(int k = 0; k < array_counter; k++){

		distances[k] =  findedges(edges[k], target, previous, counter + 1);		
	
	}


	// Finding the lowest counter and returning.
	for(int k = 0; k < array_counter; k++){

		try {
			if((distances[k] < min)){

				min = distances[k];
			}	

		}catch(ArrayIndexOutOfBoundsException e){

			// Do nothing;
		}
	
	}
	

	if(min == 0 || min == -1){
	
		return -1;

        }else{
		

		return min;
	}

    }
     
    public boolean[][] resizeArray(boolean[][] adjmatrix){

	boolean temparray[][] = new boolean[verlabels.size()][verlabels.size()];

        // Checking if the size of verlabels<T> is 1;
        if(verlabels.size() == 1){

		adjmatrix = new boolean[1][1];
                adjmatrix[0][0] = false;
	
		return adjmatrix;

	}


	for(int i = 0; i < verlabels.size(); i++){
		for(int k = 0; k < verlabels.size(); k++){

			try{

				temparray[i][k] = adjmatrix[i][k];

			}catch(ArrayIndexOutOfBoundsException e){

				// Do nothing.
			}

		}
	}
		
	// Creating a new size array;
	adjmatrix = new boolean[verlabels.size()][verlabels.size()];

	for(int i = 0; i < verlabels.size(); i++){
		for(int k = 0; k < verlabels.size() ; k++){
			
			try{
	
				adjmatrix[i][k] = temparray[i][k];

			}catch(ArrayIndexOutOfBoundsException e){

				// Do nothing.
			}
		}
	}	

	return adjmatrix;
    }
	
    public int findvertex(T vertex){

	// Variable for use;
	int location = -1;

	// Searching for the column/row location of the vertLabel;
	// Checking the Label location with a for loop;
	for(int i = 0; i < verlabels.size(); i++){

		try{
			if(verlabels.get(i).equals(vertex)){

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
