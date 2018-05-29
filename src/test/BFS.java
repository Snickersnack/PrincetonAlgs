package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.Queue;

public class BFS {

	Map<Node, List<Node>> map;
	Node a;
	Map<Node, Node> storage;
	
	public BFS(){
		map = new HashMap<Node, List<Node>>();
		storage = new HashMap<Node, Node>();

		
		try{
			File file = new File("/Users/wilsontan/Downloads/princeton/bfs.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
	       
			
			String st = "";
			int count = 0;
	        while((st=br.readLine()) != null){
	    		String[] split1 = st.split("([^A-Z])+");
	    		System.out.println(Arrays.toString(split1));
	    		
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
		
		public Node(String value){
			this.value = value;
			visited = false;
		}
		
		@Override
		public String toString(){
			return this.value;
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
		StringBuilder sb = new StringBuilder();
		Queue<Node> bfsQ = new Queue();
		bfsQ.enqueue(a);
		while(!bfsQ.isEmpty()){
			Node node = bfsQ.dequeue();
			sb.append(node.value + " ");
			node.visited = true;
			for(Node item : map.get(node)){
				if(!item.visited){
					bfsQ.enqueue(item);
					item.visited = true;
				}
				
			}
		}
		return sb.toString();
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
