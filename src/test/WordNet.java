package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//create graph and do a topological sort to see if we're in a rooted acyclical graph
public class WordNet {

	
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IOException {
		File synFile = new File(synsets);
		File hypFile = new File(hypernyms);
		BufferedReader br = new BufferedReader(new FileReader(synFile));
		String st;
		List<Synset> synsetMap = new ArrayList<Synset>();
		//OR
		//WE can do multiple arrays. List of noun array, glossary array. No ID necessary. '
		//Also do an outbound/inbound array? How would we keep track of hypernyms? 
		//Matrix...how would we deal with multiple outgoing?
		
        while((st=br.readLine()) != null){
        	
        }
        
        
		
		
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		List<String> returnList = new ArrayList<String>();

		return returnList;
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return true;
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		int distance = 0;
		return distance;
	}

	// a synset (second field of synsets.txt) that is the common ancestor of
	// nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		String sap = "";
		return sap;
	}

	// do unit testing of this class
	public static void main(String[] args) {
	}
	
	public class Synset{
		int id;
		Set<String> nouns;
		String gloss;
		Set<Synset> hypernyms;
		public Synset(int id, Set<String> nouns, String gloss){
			this.id = id;
			this.nouns = nouns;
			this.gloss = gloss;
		}
		@Override
		public boolean equals(Object o){
			if(!(o instanceof Synset)){
				return false;
			}
			Synset set = (Synset)o;
			return this.id == set.id;
		}
		
		@Override
		public int hashCode(){
			int result = 17;
			result = result * 31 + this.id;
			return result;
		}
	}
	

}
