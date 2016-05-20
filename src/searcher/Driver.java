package searcher;

import java.io.File;
import java.util.Scanner;

public class Driver {

	private BinTree tree = new BinTree();



	public static void main(String[] args) {

		Driver prgm = new Driver();

		prgm.run();

	}

	public void run(){

		System.out.println("Welcome to the Binary Tree word finder.\n"
				+ "Please wait for contents to load...\n");

		System.out.println("What is the name of the file you would like to read?");
		Scanner sc = new Scanner(System.in);
		
		readFile(sc.next());
		System.out.println("Ready!");

		tree.countWords();

		tree.wordSearch("and");  //Spelling is precise!
		tree.wordSearch("or");
		tree.wordSearch("he");
		tree.wordSearch("she");
		tree.wordSearch("they");

		System.out.println("\nThere are " 
				+ tree.getDepth() + " levels.");

		tree.findUnique();

		tree.rootWord(); 

		tree.wordAtDeepest();

		tree.mostFreq();

	}



	/**
	 * This method takes in the name of the file, opens it,
	 * and then outputs a String containing the contents of
	 * the file.
	 */

	private void readFile(String filename){

		Scanner myFile;

		try {
			String text ="";

			myFile = new Scanner(new File(filename));

			// Read and build the String in the file
			while(myFile.hasNext()){

				text = myFile.next();

				//Only letters and numbers in the string
				text = text.replaceAll("[^A-Za-z0-9]", "");

				//If we have an empty string move on to the next string
				if(text.equals("")){
					continue;
				}
				tree.add(text.toUpperCase());

			}

			//Close the file
			myFile.close();


		} catch (Exception e){
			System.out.println("Could not find file");
		}

	}


}
