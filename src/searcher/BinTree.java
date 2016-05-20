package searcher;


public class BinTree {

	private Node root;


	/**
	 * Converts to uppercase and calls the recursiveAdd()
	 */
	public void add(String newWord) {

		//Change the word case to make comparing easier
		newWord = newWord.toUpperCase();

		root = recursiveAdd(root, newWord);
	}

	/**
	 * Takes the root and recurses until the root is null (base case)
	 * Frequency is incremented if the data is being added, or if
	 * it already exits. If the data is not present, the method recurses
	 */
	private Node recursiveAdd(Node subTree, String newWord) {

		//Base case: the node is null
		//Empty trees have a node created, set, and incr freq
		if (subTree == null) {

			subTree = new Node();
			subTree.setStoredWord(newWord);
			subTree.incrFreqCount(); 
			return subTree;

		}

		int comparison = newWord.compareTo(subTree.getStoredWord());

		//For a word already in tree, increment the frequency
		if (comparison == 0) {

			subTree.incrFreqCount();
			return subTree;

			//The root comes before the new word, then 
			//move on to the left child
		} else if(comparison < 0) {

			subTree.setLchild(recursiveAdd(subTree.getLchild(), 
					newWord));

		} else { //if(comparison > 0) {

			subTree.setRchild(recursiveAdd(subTree.getRchild(), 
					newWord));

		}
		return subTree;
	}





	/**
	 * Calls countRecur() and prints the number of words
	 */
	public void countWords(){
		System.out.println("There are " + countRecur(root, 1)
				+ " words in the text.");
	}


	/**
	 * Pulls the frequency from each node in the tree, 
	 * using pre-order traversal
	 */
	private int countRecur(Node subTree, int coun){

		//Base case: the node is null, so return 
		//the count of words
		if(subTree == null){
			return coun;
		} 

		//add the frequency of the word to the count
		coun += subTree.getFreqCount();

		//Find the count in the left branches
		coun = countRecur(subTree.getLchild(), coun);

		//Find the count in right branches and return
		return countRecur(subTree.getRchild(), coun);
	}






	/**
	 * Takes String in from user, converts it to upper case,
	 *  and then calls the recursive method wordSearchRecur()
	 */
	public void wordSearch(String lookForWord){

		lookForWord = lookForWord.toUpperCase();
		wordSearchRecur(root, lookForWord);

	}


	/**
	 * Method traverses the tree in pre-order. Prints out
	 * the word and its frequency when the word is found
	 */
	private void wordSearchRecur(Node subTree, String lookForWord){

		//Base case
		// The root is that same as the string
		if(subTree == null){
			System.out.println("The word \"" + lookForWord + "\" is not "
					+ "found in the text");
			return;
		}

		int comparison = lookForWord.compareTo(subTree.getStoredWord());

		if(comparison == 0){
			System.out.println("The word \"" + lookForWord 
					+ "\" is found " + subTree.getFreqCount() 
					+ " times in the text");


			//Alphabetically before, then move to left branch
		} else if (comparison < 0){
			wordSearchRecur(subTree.getLchild(), lookForWord);

			//Alphabetically after, then move to right branch
		} else { // if(comparison > 0){
			wordSearchRecur(subTree.getRchild(), lookForWord);
		}	

	}






	/**
	 * Calls the recursive getDepth() method, and returns the 
	 * total depth of the tree
	 */
	public int getDepth() {
		return getDepth(root, 0, 0);
	}

	/**
	 * Recursive method which compares the level of the node
	 * to the highest level up to that point. Returns the 
	 * total depth of the binary tree
	 */
	private int getDepth(Node root, int level, int maxLevel) {

		//Base case: the root is null
		if (root == null) {
			return maxLevel+1;
		}

		//If the level we are on is bigger than the largest level
		//up to this point, then it becomes the largest
		if (level > maxLevel) {
			maxLevel = level;
		}


		int x = getDepth(root.getLchild(), level+1, maxLevel);
		int y = getDepth(root.getRchild(), level+1, maxLevel);

		//If the left child is greater than the right child, 
		//then return the left value. Otherwise, return the right.
		return x > y ? x : y;
	}




	/**
	 * Calls the recursive findUniqueWords() method, then
	 * takes the returning value and outputs it to the user
	 */
	public void findUnique() {

		System.out.println("There are " + findUniqueWords(root, 0)
				+ " unique words.");
	}

	/**
	 * Traverses through the tree from the root, to the left
	 * branch, then to the right branch (Pre-order). Every
	 * time that there is a word that only occurs once, it
	 * increments the count of unique words, and returns 
	 * the number of unique words in text
	 */
	private int findUniqueWords(Node subTree, int uniqueCount) {

		// Base Case: At the end of the branch
		if(subTree == null){
			return uniqueCount;

			//The word occurs exactly one time
		} else if(subTree.getFreqCount() == 1){
			uniqueCount++;
		}


		//Find the count in the left branches
		uniqueCount = findUniqueWords(subTree.getLchild(), uniqueCount);

		//Find the count in right branches and return
		return findUniqueWords(subTree.getRchild(), uniqueCount);
	}




	/**
	 * Returns the word at the root of the tree
	 */
	public void rootWord(){

		System.out.println("\"" + root.getStoredWord()
				+ "\" is the word at the root of the tree.");
	}





	/**
	 * Calls the recursive method deepestWord(), and prints
	 * it to the console
	 */
	public void wordAtDeepest(){

		System.out.println("\"" + deepestWord(root, 1, getDepth(), "-") 
				+ "\" is the deepest word in the tree.");

	}


	/**
	 * Recursive method which traverses tree in pre-order and 
	 * returns the word, once the deepest level is reached
	 */
	private String deepestWord(Node subTree, int curLevel, 
			int deepestLevel, String word) {

		// Base Case
		if(subTree == null){
			return word;

			//We are at the deepest level, so return the stored word
		} else if(curLevel == deepestLevel){
			return subTree.getStoredWord();
		}

		curLevel++;

		//Find the count in the left branches
		word = deepestWord(subTree.getLchild(), curLevel, 
				deepestLevel, word);

		//Find the count in right branches and return
		word = deepestWord(subTree.getRchild(), curLevel, 
				deepestLevel, word);

		return word;
	}






	/**
	 * Calls the recursive mostFreqWord() method, then
	 * takes the returning value and outputs it to the user
	 */
	public void mostFreq() {

		System.out.println("\"" + mostFreqWord(root, 0, "") + 
				"\" is the most frequently used word.");
	}

	/**
	 * Traverses through the tree from the root, to the left 
	 * branch, then to the right branch (Pre-order). Every 
	 * time that there is a word that only occurs once, it 
	 * increments the count of unique words
	 */
	private String mostFreqWord(Node subTree, int freqNum, String word) {
		// Base Case
		if(subTree == null){
			return word;

			//If the frequency is smaller than the new node's freq
		} else if(freqNum < subTree.getFreqCount()){
			word = subTree.getStoredWord();
			freqNum  = subTree.getFreqCount();
		}

		//Find the count in the left branches
		word = mostFreqWord(subTree.getLchild(), freqNum, word);

		//Find the count in right branches and return
		return mostFreqWord(subTree.getRchild(), freqNum, word);
	}





	/**
	 * calls the recursive method printWordListRecur()
	 */
	public void printWordList() {
		printWordListRecur(root);
	}

	/**
	 * Recursive method that uses in-order traversal to print
	 * the contents of the tree
	 */
	private void printWordListRecur(Node root) {
		// Base Case
		if (root == null) {
			return;
		}

		printWordListRecur(root.getLchild());
		System.out.println(root.getStoredWord() + "  " 
				+ root.getFreqCount());
		printWordListRecur(root.getRchild());
	}

}
