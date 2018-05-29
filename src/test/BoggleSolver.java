package test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BoggleSolver {

	private BoggleBoard board;
	private Trie trieStore;
	private Set<String> allWords;
	private Die[][] storedAdj; 
	private int count;
	
	private class Die{
		protected List<Point> adj;
		
	}

	public BoggleSolver(String[] dictionary) {
		allWords = new HashSet<String>();
		trieStore = new Trie();
		for (String word : dictionary) {
			trieStore.put(word, word);
		} 
		count = 0;
	}


	public Iterable<String> getAllValidWords(BoggleBoard board) {
		allWords.clear();
		this.board = board;
		System.out.println(board);
		int r = board.rows();
		int c = board.cols();
		boolean[] visited = new boolean[r*c];

		storedAdj = new Die[r][c];

		long start = System.currentTimeMillis();

		preCompute(r, c);

		
		TrieNode root = trieStore.getRootNode();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				Point coord = new Point(i, j);	
				recurse(coord, visited, "", root);
			}
		}
		

		System.out.println("size: " + allWords.size());
		System.out.println(allWords);
		System.out.println("count: " + count);
		
		long end = System.currentTimeMillis();

		System.out.println("Total time spent = " + (end - start)
				+ " milli seconds.");
		
		
		return allWords;
	}
	
	
	private void preCompute(int r, int c){
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				Point coord = new Point(i, j);
				Die die = new Die();
				die.adj = getSurroundingLetters(coord);
				storedAdj[i][j] = die;

			}
		}
		
	}
	private List<Point> getSurroundingLetters(Point arr) {
		List<Point> letterList = new ArrayList<Point>();
		int row = arr.x;
		int col = arr.y;
		int rowLLimit = row - 1;
		int rowULimit = row + 1;
		int colLLimit = col - 1;
		int colULimit = col + 1;

		if (row == 0) { // if row (i) is 0, we don't check top row. if col(j) is
						// 0, we don't check left row
			rowLLimit = row;
		}
		if (col == 0) {
			colLLimit = col;
		}
		if (row == board.rows() - 1) {
			rowULimit = row;
		}
		if (col == board.cols() - 1) {
			colULimit = col;
		}
		for (int i = rowLLimit; i <= rowULimit; i++) {
			for (int j = colLLimit; j <= colULimit; j++) {
				if (!(i == row && j == col)) {
					Point coords = new Point(i, j);
					letterList.add(coords);
				}
			}
		}
		return letterList;
	}
// Alternate implementation from another student @ Coursera. Node is a node in the trie dictionary. Boolean array makes his faster than my hashset due to hashing
//	private void getWordsFrom(String str, Node node, int i, int j, boolean[] marked) {
//	    if (node.hasWord() && str.length() > 2) words.add(str);  // store word in a HashSet instance variable, if it is in the dictionary
//	    
//	    for (int i1 = i - 1; i1 <= i + 1; i1++) {
//	        for (int j1 = j - 1; j1 <= j + 1; j1++) {
//	            if (isValidBlock(i1, j1, marked)) {  // check if it is out of bounds or already visited
//	                char letter = myBoard.getLetter(i1, j1);  // board saved as instance variable
//	                String nextStr = appendStr(str, letter);
//	                Node nextNode = dictionary.nextNode(node, letter);  // go one level deeper in the trie with the next char
//	                
//	                if (nextNode != null) getWordsFrom(nextStr, nextNode, i1, j1, nextMarked(marked, i1, j1));  // nextMarked() returns a deep copy of the array, and marks the current cell as visited
//	            }
//	        }
//	    }
//	}
	
	private void recurse(Point arr, boolean[] visited, String word, TrieNode node) {
		count++;
		int position = (arr.x)*board.cols() + arr.y;
		visited[position] = true;
		String letter = Character.toString(board.getLetter(arr.x, arr.y));
		if (letter.equals("Q")) {
			letter = "QU";
		}
		word = word + letter;
//		System.out.println("Current word: " + word);
		
		int arrIndex = Character.getNumericValue(letter.charAt(0)) - 10;
		TrieNode current = node.children[arrIndex];
		
		if (current == null) {
			visited[position] = false;
			return;
		}

		
		//if next is not null and it's a Q, then we move on to U
		if(arrIndex == 16){
			arrIndex = 20;
			current = current.children[arrIndex];
		}
		
		if (current == null) {
			visited[position] = false;
			return;
		}
		
		if (word.length() >= 3) {
			if (current.value != null) {
				allWords.add(word);
			}
		}

		for (Point coord : storedAdj[arr.x][arr.y].adj) {
			int pos = (coord.x)*board.cols() + coord.y;
			System.out.println(board.rows() + " " + board.cols());
			System.out.println(pos);
			System.out.println(coord.x + " " + coord.y);
			if (visited[pos] == false) {
				recurse(coord, visited, word, current);
			}
		}
		visited[position] = false;
	}

	public int scoreOf(String word) {
		int pointValue;
		int length = word.length();

		if (length < 3) {
			return 0;
		}

		if (length < 5)
			pointValue = 1;
		else if (length == 5)
			pointValue = 2;
		else if (length == 6)
			pointValue = 3;
		else if (length == 7)
			pointValue = 5;
		else
			pointValue = 11;
		return pointValue;

	}

}
