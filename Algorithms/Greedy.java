package Algorithms;

import java.util.ArrayList;

import util.Stopwatch;
import util.Vertex;

public class Greedy {
	
	Vertex[] vertices;
	int[][] distanceMatrix;
	ArrayList<Vertex> optimal = new ArrayList<Vertex>(); // The optimal path for the tour.
	int optimalDistance = 0;
	
	public Greedy(Vertex[] vertices){
		this.vertices = vertices;
		this.distanceMatrix = Vertex.constructDistanceMatrix(vertices);
	}
	

	
	public void greedyPath(){ 
		Vertex Vi = vertices[0];
		int shortestPath = distanceMatrix[Vi.vertexNumber][0];			// determines the shortest path out of Vi
		int shortestPathVertex = 0;  	// The vertex that shortestPath leads to
		
		optimal.add(Vi);			// Add the starting vertex to the list
		
		if ( Vi.vertexNumber == 0){
			shortestPath = distanceMatrix[Vi.vertexNumber][1];
			shortestPathVertex = 1;
		}
		
		while(optimal.size() < vertices.length){
			for( int j = 0 ; j < vertices.length ; j++){
				while( vertexHasBeenVisited(vertices[shortestPathVertex]) && j!=vertices.length-1 ) {     // If a new candidate for the shortest path out of Vi is connected to a node we have already visited and we are not at the last element which would cause array out of bounds if we add to it
					if( distanceMatrix[Vi.vertexNumber][j+1] == 0){          // If the next distance happens to be zero
						shortestPath = distanceMatrix[Vi.vertexNumber][j+2]; // Adding two to j because we know that you will never have two zeros in a row
						shortestPathVertex = j+2;
						j+=2;
					}
					else{
					shortestPath = distanceMatrix[Vi.vertexNumber][++j];
					shortestPathVertex = j;
					}
				}
			
				if(distanceMatrix[Vi.vertexNumber][j] < shortestPath && distanceMatrix[Vi.vertexNumber][j]!=0 && !vertexHasBeenVisited(vertices[j])){ 
					shortestPath = distanceMatrix[Vi.vertexNumber][j];
					shortestPathVertex = j;
					}
			}
		
		optimal.add(vertices[shortestPathVertex]); // Add to optimal the vertex that is at the other end of the shortest path out of Vi
		Vi = vertices[shortestPathVertex];
		shortestPath = distanceMatrix[Vi.vertexNumber][0];
		shortestPathVertex = 0;
		}
		optimal.add(vertices[0]);
		/*for( Vertex v : optimal){     // this is just for printing the optimal list
			System.out.println(v.vertexNumber);
		}*/
	}
	
	public ArrayList<Vertex> getOptimal(){
		greedyPath();
		return optimal;
	}
	
	public int optimalDistance(){
		for ( int i = 0 ; i<vertices.length ; i++){
			optimalDistance += optimal.get(i).distance(optimal.get(i+1));
		}
		return optimalDistance;
	}
	
	private boolean vertexHasBeenVisited(Vertex v){
		boolean exist = false;
		for (Vertex vi : optimal){
			if (vi == v){
				exist = true;
			}
		}
		return exist;
	}
	
	public void printArray(){
		for (int i = 0 ; i<vertices.length ; i++){
			System.out.println();
			for(int j = 0 ; j<vertices.length ; j++){
				System.out.print(distanceMatrix[i][j]+" ");
				
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Vertex example = new Vertex(16);
		Vertex[] vertices = example.getArrayOfVertices();
		Greedy x = new Greedy(vertices);
		x.printArray();
		Stopwatch w = new Stopwatch();
		ArrayList<Vertex> path = x.getOptimal();
		double g = w.elapsedTime();
		int y = x.optimalDistance();
		System.out.println(y);
		System.out.println(g);
		for( Vertex k : path){
			System.out.println(k.vertexNumber);
		}

	}

}
