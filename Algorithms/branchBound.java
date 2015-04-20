package Algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

import util.Vertex;
import util.node;
import util.priorityQueue;

public class branchBound {

	 static Vertex[] vertices;
	
	public branchBound( Vertex[] vertices){
		this.vertices = vertices;
	}
	
	public  ArrayList<Vertex> branchNbound(){
		ArrayList<Vertex>opttour = new ArrayList<Vertex>();
		priorityQueue pq = new priorityQueue();
		node u = new node();
		node v = new node();
		int vBound;
		int uBound;
		
		v.setLevel(0);
		v.addPath(vertices[0]);
		vBound = v.getBound();
		int minLength = 4000;
		pq.insert(v);

		while(!pq.isEmpty()){
			v = pq.removeBest();
			if( v.getBound() < minLength){
				u.level = v.level + 1;
outer:			for ( int i = 1 ; i <vertices.length ; i++){
					while(v.existInPath(vertices[i]))
					{	i++;
						if(i>= vertices.length)break outer;}
					u.path = (ArrayList<Vertex>) v.path.clone();
					u.addPath(vertices[i]);	
					u.printPath();
					if ( u.level == vertices.length - 2){
inner:					for(int j = 0 ; j<vertices.length ; j++){
							while(u.existInPath(vertices[j])){
								j++;
								if(j >= vertices.length)break inner;
							}
							
							u.addPath(vertices[j]);
							u.printPath();
							System.out.println(u.length());
}
						u.addPath(vertices[0]);
						u.printPath();
						System.out.println(u.length());
						if(u.length() <= minLength){
							minLength = u.length();
							opttour = u.path;
							
						}
					}
					else{
						u.getBound();
						if(u.bound < minLength){
							ArrayList<Vertex> tmp = (ArrayList<Vertex>) u.path.clone();
							node tmpnode = new node();
							tmpnode.level = u.level;
							tmpnode.path = tmp;
							pq.insert(tmpnode);
							//pq.printPath();
						}
					}
				
					}
						
			}
	}
	return opttour;

}
	
	
	
	public static void main(String[] args) {
		Vertex x = new Vertex(8);
		branchBound y = new branchBound(x.getArrayOfVertices());
		x.printDistance();
		ArrayList<Vertex> tour = y.branchNbound();
		
		for(Vertex a : tour){
			System.out.println(a.vertexNumber);
		}
	
	}
}