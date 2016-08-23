import java.io.PrintWriter;
import java.io.File;
import java.io.*;
import java.util.*;


public class Generator{

	// Main
	public static void main(String[] args) throws IOException{

		// 1st arg is the range from 0 - input;
		// 2nd arg is how many lines to generate;

		// Variables for use;
		int max = Integer.parseInt(args[0]);
		int density = Integer.parseInt(args[1]);
	
	 	// Creating Printwriter;
		PrintWriter data = new PrintWriter(new FileWriter("data.txt"));

		// Creating a random;
		Random rand_ver = new Random();

		// Printing to stuff;
		for(int i = 0; i < density; i++){

			int data1 = rand_ver.nextInt(max) + 1;
			int data2 = rand_ver.nextInt(max) + 1;

			// Checking if they're the same;
			if(data1 == data2){

				i = i - 1;

			}else{

				data.print(data1);
				data.print(" ");
				data.print(data2);
				data.print("\n");

			}

		}	
		
		//Closing;
		data.close();

		System.out.println("Done.");

	}

}