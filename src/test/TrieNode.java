package test;


public class TrieNode{
	
	String value;
	TrieNode[] children;
	
	public TrieNode(){
		children = new TrieNode[26];
		value = null;
	}

	
	@Override
	public int hashCode(){
		int result = 17;
		result = result * 31 + this.value.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof TrieNode)){
			return false;
		}
		TrieNode object = (TrieNode)o;
		return (object.value.equals(this.value));
	}
}
