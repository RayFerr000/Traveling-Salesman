package util;

public class Edge {
	
	int weight;
	Vertex v1;
	Vertex v2;
	
	public Edge(Vertex v1 , Vertex v2){
		
		this.v1 = v1;
		this.v2 = v2;
		weight = distance(v2, v1);
	}
	
	private int distance(Vertex v1 , Vertex v2){
		
		double exp1 = (v2.x - v1.x);  
		double exp2 = (v2.y - v1.y);
		
		exp1 = Math.pow(exp1, 2);     
		exp2 = Math.pow(exp2, 2);
		
		weight = (int) Math.sqrt(exp1 + exp2);
		
		return weight;
		
	}
	

	
	/**public static void main(String args[]){
		Vertex v1 = new Vertex (-2, 1);
		Vertex v2 = new Vertex (1, 5);
		
		Edge e1 = new Edge(v1, v2);
		System.out.println(e1.weight);

	}*/

}


