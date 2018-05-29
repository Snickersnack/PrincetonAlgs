package test;

import java.util.LinkedHashSet;

public class KMP {

	private static LinkedHashSet<Character> states;
	// A C A C A B A B
	public static void solve(String str){
		precalc(str);
		
	}
	
	public static void precalc(String str){
		//n complexity
		for(int i = 0; i<str.length(); i++){
			states.add(str.charAt(i));
		}
		char[] state = new char[states.size()];
		int index = 0;
		for(Character letter : states){
			state[index] = letter;
			index++;
		}
		int[][] reference = new int[states.size()][str.length()];
		int x = 0;
		
		//populate first item in table
		//THIS IS N*M COMPLEXITY WTF?
		for(int i = 0; i<states.size(); i++){
			if(state[i] == str.charAt(0)){
				reference[i][0] = 1;
			}else{
				reference[i][0] = 0;
			}
		}
		
		
		for(int i = 0; i<str.length(); i++){
			for(int j =1; j<states.size(); j++){
				if(reference[i][j] == str.charAt(j)){
					reference[i][j] = j+1;
				}else{
					reference[i][j] = reference[i][x];
				}	
				
				
			}
		}
	}
}
