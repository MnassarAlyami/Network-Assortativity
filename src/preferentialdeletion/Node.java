/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preferentialdeletion;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Mnassar Alyami
 */
public class Node {
   
	static ArrayList<Node> adjNode;
	static int numOfEdges;
	
	public Node(int name) {
		super();
		this.adjNode = new ArrayList<>();
		this.numOfEdges = 0;
	}
	
        
	public void setAdjacentnode(ArrayList<Node> adjacent) {
		this.adjNode = adjacent;
	}
        
        public ArrayList<Node> getAdjacentnode() {
		return adjNode;
	}
        
        public void setNumOfEdges(int numOfEdges) {
		this.numOfEdges = numOfEdges;
	}
        
	public int getNumOfEdges() {
		return numOfEdges;
	}
        

}