package util;

import java.util.ArrayList;

public class node {
	
	public int level;
	public ArrayList<Vertex> path = new ArrayList<Vertex>();
	public int bound;
	
	
	public node(){
	};
	
	
	public void setLevel(int x){
		this.level = x;
	}
	
	public ArrayList<Vertex> getPath(){
		ArrayList<Vertex> tmp = path;
		return tmp;
	}
	 
	public void addPath(Vertex v){
		path.add(v);
	}
	
	public int getBound(){
		this.bound = Vertex.calculateBound(path);
		return bound;
		}
	
	public boolean existInPath(Vertex Vi){
		boolean exist = false;
		for( int i = 0 ; i < path.size() ; i++){
			if( Vi.vertexNumber == path.get(i).vertexNumber){
				exist = true;
			}
		}
		return exist;
	}
	
	public int length(){
		int length = 0;
		for ( int i = 0 ; i < path.size()-1  ; i++){
			length += path.get(i).distance(path.get(i+1));
		}
		return length;
		
	}
	
	public void printPath(){
		System.out.println();
		for( int i = 0 ; i<path.size(); i++){
			System.out.print(path.get(i).vertexNumber + " ");
		}
		System.out.println(" - - >" + this.getBound());
		System.out.println();


	}
	public int calculateBound(ArrayList<Vertex> path ){					
		this.bound = Vertex.calculateBound(path);
		return bound;
	}
	

	

}

	


