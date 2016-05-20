package searcher;

public class Node {
	
	private String storedWord;
	private int freqCount;
	private Node lchild;
	private Node rchild;
	//getters & setters are below
	
	public Node(){
		freqCount = 0;
	}
	
	public String getStoredWord() {
		return storedWord;
	}
	public void setStoredWord(String data) {
		this.storedWord = data;
	}
	public int getFreqCount() {
		return freqCount;
	}
	public void incrFreqCount() {
		freqCount++;
	}
	public Node getLchild() {
		return lchild;
	}
	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}
	public Node getRchild() {
		return rchild;
	}
	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}
}
