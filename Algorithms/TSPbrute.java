package Algorithms;

import java.util.ArrayList;

import util.Vertex;

public class TSPbrute {
	
	private Vertex[] tour;   
	private Vertex[] optimal;
	int oldDistance=0;
	int newDistance=0;

    public TSPbrute(Vertex[] vertices) {
        tour = vertices;
        optimal = vertices;
        
      /*  for (int i =0 ; i < tour.length-1 ; i++){
    		
        	oldDistance+=tour[i].distance(tour[i+1]);		  // Calculate the distance of the input tour to initalize
    	}
        oldDistance+=tour[tour.length-1].distance(tour[0]); // Since this is a tour, wee need to add in the cost of going from the last vertex to the first
        
        //System.out.println(oldDistance);*/
        enumerate(0);
    }

    // Print out current permutation
    private void process() {
    	for (int i =0 ; i < tour.length-1 ; i++){
    		
        	newDistance+=tour[i].distance(tour[i+1]);		  // Calculate the distance of this tour
    	}
    	
    	newDistance+=tour[tour.length-1].distance(tour[0]);
        
    	for (int i = 0; i < tour.length; i++){

           System.out.print(tour[i].vertexNumber + " " );
        }
        //System.out.print(newDistance);

    	if( newDistance < oldDistance){
    		Vertex[] clone = tour.clone();
    		optimal = clone;

    		
    		for (Vertex x : optimal){
    			System.out.print(" "+ x.vertexNumber + " " );
    		}
            oldDistance = newDistance;

    	}
        


        newDistance=0;    	
    	
    	
        System.out.println();

    }
    
    public void result(){
    	
        for (Vertex x : optimal){

            System.out.print(x.vertexNumber + " " );
        }
        System.out.print(oldDistance);
    }
    // enumerate all solutions
    public void enumerate(int n) {

        // found a solution
        if (n == tour.length) {
        	
            process();
            return;
        }

        // continue searching
        for (int i = n; i < tour.length; i++) {
        	if(tour[0].vertexNumber != 0){
        		return;
        	}
            exch(n, i);
            for(Vertex x : tour){
            	//System.out.print(x.vertexNumber+" ");
            }
            enumerate(n+1);
            exch(i, n);
        }
    }  

    // exchange tour[i] and tour[j]
    private void exch(int i, int j) {
        Vertex temp = tour[i];
        tour[i] = tour[j];
        tour[j] = temp;
    } 


	public static void main(String[] args) {
		Vertex v0 = new Vertex(1,2,0);
		Vertex v1 = new Vertex(5,8,1);
		Vertex v2 = new Vertex(10,11,2);
		Vertex v3 = new Vertex(9,0,3);
		Vertex v4 = new Vertex(10,4,4);
		Vertex v5 = new Vertex(8,2,5);
		Vertex v6 = new Vertex(1,3,6);
		Vertex v7 = new Vertex(7,10,7);
		Vertex[] vertices = {v0,v1,v2,v7};
		Vertex example = new Vertex(32);
		Vertex[] vertices2 = example.getArrayOfVertices();

		TSPbrute x = new TSPbrute(vertices2);
		//x.result();
		
		
	}

}
