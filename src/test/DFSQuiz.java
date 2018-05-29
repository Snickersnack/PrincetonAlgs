package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DFSQuiz {

	Map<Node, List<Node>> map;
	Node a;
	Map<Node, Node> storage;
	
	
	public DFSQuiz(){
		map = new HashMap<Node, List<Node>>();
		storage = new HashMap<Node, Node>();

		
		//problem is it's not using the same nodes....
		try{
			File file = new File("/Users/wilsontan/Downloads/princeton/dfs.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
	       
			
			String st = "";
			int count = 0;
	        while((st=br.readLine()) != null){
	    		String[] split1 = st.split("([^A-Z])+");
	    		
	    		Node keyNode = new Node(split1[1]);
	    		if(count == 0){
	    			a = keyNode;
	    		}
	    		
	    		keyNode = swapStorage(keyNode);
	    		List<Node> valueList = new ArrayList();
		    		if(split1.length>2){
			    		for(int i = 2; i<split1.length; i++){
			    			Node node = new Node(split1[i]);
			    			node = swapStorage(node);
			    			valueList.add(node);
			    		}
	
		    		}
		    		map.put(keyNode, valueList);
	    		

	    		count++;
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public class Node{
		String value; 
		boolean visited;
		Integer group = null;
		
		public Node(String value){
			this.value = value;
			visited = false;
		}
		
		@Override
		public String toString(){
			return "Value: " + this.value + "Group number: " + this.group;
		}
		
		@Override 
		public boolean equals(Object o){
			if(!(o instanceof Node)){
				return false;
			}
			Node node = (Node)o;
			return node.value.equals(this.value);
					
		}
		
		@Override
		public int hashCode(){
			int result = 17;
			result = 31 * result + this.value.hashCode();
			return result;
		}
	}
	
	public String solve(){
		return solve(a);
	}
	
	public String solve(Node node){
		StringBuilder sb = new StringBuilder();
		for(Entry<Node, List<Node>> item : map.entrySet()){
			sb.append(iterate(item.getKey()) + " ");
		}


		return sb.toString();
		
	}
	
	public String iterate(Node node){
		StringBuilder sb = new StringBuilder();
		node.visited = true;
		for(Node targetNode : map.get(node)){
			if(!targetNode.visited){
				sb.append(iterate(targetNode)).toString();
			}
			
		}
		sb.append(node.value + " ");
		return sb.toString();
		
	}
	
	public String solve(Node node, int group){
		StringBuilder sb = new StringBuilder();
		node.visited = true;
		node.group = group;
		for(Node targetNode : map.get(node)){
			if(!targetNode.visited){
				sb.append(solve(targetNode, group)).toString();
			}

		}
		return sb.toString();
		
	}
	
	public int[] findConnected(){
		return findConnected(a);
	}
	
	public int[] findConnected(Node node){
		int[] answer = new int[10];
		HashSet<Integer> set = new HashSet<Integer>();
		int count = 0;
		int index = 0;
		for(Entry<Node, List<Node>> item : map.entrySet()){
			Integer number = item.getKey().group;
			if(number == null){
				solve(item.getKey(), count);
				count++;			

			}
			else{
				
				System.out.println("count: " + count);
			}
			answer[index] = item.getKey().group;
			index++;

			
		}
		return answer;
	}
	
	
	public Node swapStorage(Node node){
		Node storageNode = storage.get(node);
		if( storageNode !=null){
			return storageNode;
		}else{
			storage.put(node, node);
		}
		return node;
	}
}
