package test;


public class Trie {

	private TrieNode root;
	private int size;
	
	public Trie(){
		root = new TrieNode();
		size = 0;
	}
	
	public TrieNode getRootNode(){
		return root;
	}
	
	public int size(){
		return size;
	}
	public void put(String key, String value){
		int index = 0;
		TrieNode currentNode = root;
		while(index < key.length()){
			char c = key.charAt(index);
			int arrIndex = Character.getNumericValue(c) - 10;
			TrieNode nextNode = currentNode.children[arrIndex];
			if(nextNode == null){
				nextNode = new TrieNode();
				currentNode.children[arrIndex] = nextNode;
			}
			currentNode = nextNode;
			
			index++;
		}
		currentNode.value = value;
	}
	
	
	public boolean contains(String key){
		String answer = get(key);
		return answer != null;
	}
	
	public String get(String key){
		TrieNode node = root;
		int index = 0;
		while(index<key.length()){
			int arrIndex = Character.getNumericValue(key.charAt(index)) - 10;
			node = node.children[arrIndex];
			index++;
			if(node == null){
				break;
			}

		}
		if(node != null){
			return node.value;
		}
		return null;
		 
	}
	
	public Boolean containsPrefix(String prefix){
		TrieNode node = root;
		int index = 0;
		while(index<prefix.length()){
			int arrIndex = Character.getNumericValue(prefix.charAt(index)) - 10;
			node = node.children[arrIndex];
			index++;
			if(node == null){
				return false;
			}

		}
		return true;
		
	}

}

