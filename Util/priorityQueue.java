package util;

import java.util.ArrayList;

public class priorityQueue {
	
	
	public ArrayList<node> pq;
	public int count;
	
	
	public priorityQueue(){
		pq = new ArrayList<node>();
		count = 0;
	}
	
	
	public void printPath(){
		for( int i = 0 ; i<pq.size(); i++){
			pq.get(i).printPath();
		}
	}
	public void insert(node n){
		pq.add(n);
		count++;
	}
	

	public boolean isEmpty(){
		if(count == 0) 
			return true;
		else
			return false;
	}
	
	public node removeBest(){
		int bestBound = pq.get(0).getBound();
		node nodeOfBestBound  =  pq.get(0);
		if ( pq.size() == 1){
			pq.remove(nodeOfBestBound);
			count--;
			return nodeOfBestBound;
		}

		for ( int i = 0 ; i<pq.size(); i++){

			if ( bestBound > pq.get(i).getBound() ){
				bestBound = pq.get(i).getBound();
				nodeOfBestBound = pq.get(i);
			}
		}
		pq.remove(nodeOfBestBound);
		count --;
		return nodeOfBestBound;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
