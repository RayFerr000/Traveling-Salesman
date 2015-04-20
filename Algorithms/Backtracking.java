package Algorithms;

import java.util.ArrayList;
import java.util.Stack;

import util.Vertex;

public class Backtracking {
	Vertex[] vertices;
	int currentShortestDistance;
	int possibleShortestDistance= 0;
	
	ArrayList<Vertex> currentBestPath;								// Length of the best path we know of
	ArrayList<Vertex> possibleBestPath = new ArrayList<Vertex>();   // Length of a candidate path for the the best path
	int[] bitmap = {1,1,1,1};
	
	public Backtracking(Vertex[] vertices){
		this.vertices = vertices;
		
		Greedy path = new Greedy(vertices);
		
		currentBestPath = path.getOptimal();              // The greedy path returned by the greedy algorithm is the current best path
		currentShortestDistance = path.optimalDistance(); // total distance of that greedy path is the best path initially
		
		 
		
	}
	
	public void backTrackingAlgorithm(){
		possibleBestPath.add(vertices[0]);		// add initial vertex to the path
outer:	for(int i = 1 ; i<vertices.length ; i++){
			possibleBestPath.add(vertices[i]);	// will always be the second vertex on the possibleBestPath
			possibleShortestDistance += weightOfPath(possibleBestPath);
			System.out.println(possibleShortestDistance);
			for( int j = 1 ; j < vertices.length ; j++ ){
				if(i == j) j++;
				if(promising(vertices[j])){
					possibleBestPath.add(vertices[j]);
					possibleShortestDistance += weightOfPath(possibleBestPath);
					System.out.println(possibleShortestDistance);

				}
				if(j == vertices.length-1) break outer;
			}
				
		}
			
		
		
	}
	public boolean promising(Vertex v){
		 return (v.distance(possibleBestPath.get(possibleBestPath.size()-1)) + possibleShortestDistance < currentShortestDistance);
				                                                                                                        	   
				     
				                                                                                                           
	}

	

	
	public int weightOfPath(ArrayList<Vertex> path){
		int distance = 0;
		for ( int i = 0 ; i<path.size()-1 ; i++){
			distance += path.get(i).distance(path.get(i+1));
		}
		return distance;
	}


	public static void main(String[] args) {
		Vertex cities = new Vertex(6);
		cities.printDistance();
		Vertex[] vertices = cities.getArrayOfVertices();
		
		Backtracking x = new Backtracking(vertices);
		ArrayList<Vertex> y = x.currentBestPath;
		
		for(Vertex  a:y){
			System.out.println(a.vertexNumber);
		}
		System.out.println(x.currentShortestDistance);

		x.backTrackingAlgorithm();
		
		for(Vertex m : x.possibleBestPath){
			System.out.println(m.vertexNumber);
		}
		System.out.println(x.possibleShortestDistance);

	}

}
