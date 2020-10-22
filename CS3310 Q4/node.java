//Tomes, Christopher
//node.java
//10/21/2020


public class node {

	String name;
	
	node[] dependencies;
	node next;
	boolean built = false;
	boolean touched = false;
	
	public node(String name,int sizeOfSet) {
		this.name = name;
		dependencies = new node[sizeOfSet];
	}
	public void insert(node n) {

			int i = 0;
			for(;dependencies[i]!=null;i++){}
			dependencies[i]=n;
		
	}
	public void show() {
		for(int i =0;dependencies[i]!=null;i++){
			System.out.println(dependencies[i].name);
		}
	}
}
