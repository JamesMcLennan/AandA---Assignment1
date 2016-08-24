import java.io.PrintWriter;
import java.io.File;
import java.io.*;
import java.util.*;


public class Generator{

	// Main
	public static void main(String[] args) throws IOException{

		// 1st arg is the density, eg: 0.1, 0.2, etc;
		// 2nd arg is how many vertexs vertexs;


		// Variables for use;
		double density = Double.parseDouble(args[0]);
		int vertex = Integer.parseInt(args[1]);
		int edges = (int) Math.round((vertex * vertex) * density);

		// Creating a random;
		Random rand_ver = new Random();

		int[][] array = new int[edges][2];
	
	 	// Creating Printwriter;
		PrintWriter data = new PrintWriter(new FileWriter("data.txt"));

		for(int i = 0; i < edges; i++){
			for(int k = 0; k < 2; k++){

				array[i][k] = -1;

			}	
		}

		for(int l = 0; l < edges; l++){

			int data1 = rand_ver.nextInt(vertex) + 1;
			int data2 = rand_ver.nextInt(vertex) + 1;	

			if(data1 == data2){
				
				l = l - 1;

			}else{
			
				for(int k = 0; k < edges; k++){

					if(array[k][0] == data1 && array[k][1] == data2){

						l = l - 1;
						break;
					}else if(array[k][0] == data2 && array[k][1] == data1){

						l = l - 1;
						break;
					}else {
						
						if(array[k][0] == -1 && array[k][1] == -1){

							array[k][0] = data1;
							array[k][1] = data2;
							
							data.print(array[k][0]);
							data.print(" ");
							data.print(array[k][1]);
							data.print("\n");
			
							System.out.println(data1 + " " + data2);

							break;



						}
					}

					

				}
		
				


			}

		}
				
		//Closing;
		data.close();

		System.out.println("Done.");

	}

}