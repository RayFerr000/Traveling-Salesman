package util;

import java.util.ArrayList;

public class Vertex {
	public int vertexNumber;
	public int x;
	public int y;
	static Vertex[] vertices;
	
	public Vertex(int n){ 				// n is  the number of vertices for this particular instance of the ETSP
	
		populate(n);					//Create random x and y coordinates for each vertex
		
	}
	public Vertex(int x, int y, int number){
		this.x = x;
		this.y = y;
		this.vertexNumber=number;
	}
	public Vertex(){}
	
	private void populate(int n){
		vertices = new Vertex[n];
		for ( int i = 0 ; i< vertices.length ; i++){
			vertices[i] = new Vertex();
			vertices[i].x = (int) Math.floor( ((2*n)+1) * Math.random() );
			vertices[i].y = (int) Math.floor( ((2*n)+1) * Math.random() );
			vertices[i].vertexNumber = i;

		}
	}
	
	public int distance(Vertex v2){   // Determine the Euclidean distance between two vertices.
		double exp1 = (v2.x - this.x);  
		double exp2 = (v2.y - this.y);
		
		exp1 = Math.pow(exp1, 2);     
		exp2 = Math.pow(exp2, 2);
		
		int distance = (int) Math.sqrt(exp1 + exp2);
		
		return distance;
	}
	
	
	public Vertex[] getArrayOfVertices(){
		return vertices;
	}
	
	public static int[][] constructDistanceMatrix(Vertex[] vertices){
		int[][] distanceMatrix = new int[vertices.length][vertices.length];
		for(int i = 0 ; i<vertices.length ; i++){
			for ( int j = 0 ; j<vertices.length ; j++){
				distanceMatrix[i][j] = vertices[i].distance(vertices[j]); //Calculate distance between vertex i and j and store it in distance[i][j]
				
			}
		}
		return distanceMatrix;
	}
	
	public static int calculateBound(ArrayList<Vertex> path ){					/** path is the current path at the node. Need to calculate the bounds so you know what path to continue down */
		if(path.size() == 1){
			return initialBound(path.get(0));									/** Calculate the Bound starting at the node Vi */
		}
		int localbound = 400000;																 
		int absolutebound = 0;
		ArrayList<Vertex> verticesCalculated = new ArrayList<Vertex>();			/** VerticesCalculated is going to keep track of the node i have already calculated. After I calculate the length of the current path, I add the vertices in path to this list because they are done */

		for ( int i = 0 ; i < path.size()-1  ; i++){					             /** Calculate length of path visited so far. Add the length to the bound*/
			absolutebound += path.get(i).distance(path.get(i+1));
			verticesCalculated.add(path.get(i));
			if( i == path.size()-2){ verticesCalculated.add(path.get(path.size()-1));}  /** To avoid indexOutOfBound, I stop the loop at i<path.size-1 so you need to add the last vertex of path to verticesCalculated*/
		}
loop:	for (int j = 0 ; j < vertices.length ; j++ ){
			while ( hasBeenVisited(j,verticesCalculated) ){                      /**calculate the minimum distance between the last element on the current path and the remaining vertices*/
				if(j == path.get(path.size()-1).vertexNumber && j == vertices.length-1){				/** The last element on the path can't visit any of the vertices on the path*/
					break loop;
				}
				else if(j == path.get(path.size()-1).vertexNumber){
					j++;
				}
				else if ( j >= vertices.length-1) break loop;
				else	
					j++; 
				
			}
			localbound = Math.min(path.get(path.size()-1).distance(vertices[j]), localbound);
		}
		absolutebound+=localbound;


		localbound = 500000;
		while( verticesCalculated.size() < vertices.length){		/** Need to find the distance this vertex not on the path and every other vertex, including going back to vertex V0*/
outer:		for( int k = 1 ; k < vertices.length ; k++){
				while( hasBeenVisited(k,verticesCalculated)){       /** Find a vertex that is not in the current path */
					if(k == vertices.length-1 &&  hasBeenVisited(k,path)) break outer;
					k++;											
				}
				inner :for( int w = 0 ; w < vertices.length ; w++){
						while( (hasBeenVisited(w,path) || w == k) ){    /** If w == k then distance(a[w],a[k])=0, so skip past a[w] */
							if(k == w && w == vertices.length-1)break inner;  /** If if(k == w && w == vertices.length-1) and you don't break, you will go out of bounds */
							if(w== vertices.length - 1 && hasBeenVisited(w,path) ) break inner;
							if( w == 0)break;
			
							w++;
						}
						localbound = Math.min(vertices[k].distance(vertices[w]), localbound);
					}
				verticesCalculated.add(vertices[k]);
				absolutebound+=localbound;
				localbound = 500000;
			}
		}
		
		return absolutebound ;
	}
	
	public static boolean hasBeenVisited(int Vi, ArrayList<Vertex> path){
		boolean visited = false;


		for( int i = 0 ; i<path.size(); i++){
			if( i > vertices.length) break;
			if(path.get(i) == vertices[Vi]){
				visited = true;
			}
		}
		return visited;
	}
	
	private static int initialBound(Vertex Vi){
		int localbound = 500000;																 
		int absolutebound = 0;
		ArrayList<Vertex> verticesOnBound = new ArrayList<Vertex>();
		
		while (verticesOnBound.size() <= vertices.length-1 ){
			for( int i = 0 ; i<vertices.length ; i++){
				if( Vi.vertexNumber == i && i!=vertices.length-1) i++;
				if( Vi.vertexNumber == i && i == vertices.length-1) break;
				localbound = Math.min(Vi.distance(vertices[i]),localbound);
			}
			absolutebound +=localbound;
			localbound = 500000;
			verticesOnBound.add(Vi);
			if( Vi.vertexNumber != vertices.length-1)
				Vi = vertices[Vi.vertexNumber+1];
		}
		return absolutebound;
	}
	
	public void printDistance(){
		int[][] z = constructDistanceMatrix(vertices);

		for(int i = 0 ; i<z.length ; i++){
			System.out.println();
			for(int j = 0 ; j<z.length ; j++){
				System.out.print(z[i][j]+ " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		Vertex x = new Vertex(32);
		Vertex[] y = x.getArrayOfVertices();
		x.printDistance();
		ArrayList<Vertex> test = new ArrayList<Vertex>();
		test.add(y[0]);
		test.add(y[4]);
		test.add(y[6]);
		test.add(y[5]);
		int bound = calculateBound(test);
		//System.out.println(hasBeenVisited(1,test));
		System.out.println(bound);
		
		/*for(int i = 0 ; i<8 ; i++){
		
		System.out.println("V"+ i + " = " + y[i].x + " , " + y[i].y);}
	*/}

}
