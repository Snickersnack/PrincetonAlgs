package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Main {

	public static void main(String[] args){
		
		char[][] chaArr = {{'U',  'H',  'I',  'Y'},
		{'H',  'H',  'A',  'E'},  
		{'E',  'A',  'I',  'A'},  
		{'I',  'L',  'P',  'R'}};
		BoggleBoard board = new BoggleBoard(chaArr);

		
		
        String filename = "/Users/wilsontan/Downloads/boggle/board-dichlorodiphenyltrichloroethanes.txt";
        BoggleBoard board4 = new BoggleBoard(filename);
        In in = new In(new File("/Users/wilsontan/Downloads/boggle/dictionary-yawl.txt"));
        String[] dictionary = in.readAllStrings();

        
        
        // create the Boggle solver with the given dictionary
        BoggleSolver solver = new BoggleSolver(dictionary);
        
        

        solver.getAllValidWords(board4);
        
        System.out.println(solver.scoreOf("TOGETHER"));

       
	}
	   private static List<int[]> getSurroundingLetters(int[] arr, BoggleBoard board){
		   
		   List<int[]> letterList = new ArrayList();
		   int row = arr[0];
		   int col = arr[1];
		   int rowLLimit = row - 1;
		   int rowULimit = row + 1;
		   int colLLimit = col - 1;
		   int colULimit = col + 1;
		   
		   if(row == 0){ //if row (i) is 0, we don't check top row. if col(j) is 0, we don't check left row
			   rowLLimit = row;
		   }
		   if(col == 0){
			   colLLimit = col;
		   }
		   if(row == board.rows()-1){
			   rowULimit = row;
		   }
		   if(col == board.cols()-1){
			   colULimit = col;
		   }
		   for(int i = rowLLimit; i<= rowULimit; i++){
			   for(int j = colLLimit; j <= colULimit; j++){
				   if(!(i == row && j == col)){
					   int[] coords = new int[2];
					   coords[0] =i;
					   coords[1] = j;
					   letterList.add(coords);
				   }
			   }
		   }
		   
		   return letterList;
	   }
}
