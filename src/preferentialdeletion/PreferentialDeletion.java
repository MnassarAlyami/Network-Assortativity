/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preferentialdeletion;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import java.util.*;

/**
 *
 * @author Mnassar Alyami
 // Extension for our last project.
 // https://github.com/MnassarAlyami/Preferential-Deletion
 // In this project, we measure and plot the dynamic graph assortativity.
 */

public class PreferentialDeletion {

	public static int i;
	
        //Total Degree
	public static int Deg;
	static ArrayList<Node> nodeList = new ArrayList<>();
	//Birth Probability
	static ArrayList<Integer> BProb = new ArrayList<>();
        //Death Probability
	static ArrayList<Integer> DProb = new ArrayList<>();
        // Cumulative Birth Prob 
	static ArrayList<Integer> CBProb = new ArrayList<>();
	
	static Random rand = new Random();
        
       // Return Length Method
       public static int len(ArrayList<?> list){
		return list.size();
	}

       //Cumulative Probability
       public static Node CumProb(){

		int Cum_BProb=0;
		
		for(int k = 0 ; k < len(nodeList); k++){
			Cum_BProb=Cum_BProb+BProb.get(k);
			CBProb.add(Cum_BProb);
		}
		double y=rand.nextInt(10);
		y=y/10;
		
                for(int k = 0; k < len(CBProb) ; k++){
			if (CBProb.get(k)>=y){
				Node node=nodeList.get(k);
				return node;
			}
			if (k==(len(CBProb)-1))
				return nodeList.get(k);
		}
		return null;
	}

       public static void Start(){
		i=1;
		nodeList.add(new Node(i));
		i=i+1;
		nodeList.get(0).getAdjacentnode().add(nodeList.get(0));
		nodeList.get(0).setNumOfEdges(1);
		Deg=1;
		BProb.add(1);
		DProb.add(1);
	}

//Birth Method
	@SuppressWarnings("static-access")
	public static void Birthnode(Node nodeSelected){
		Node node1= new Node(i);
		nodeList.add(node1);
		Node.adjNode.add(nodeSelected);
		Node.numOfEdges=1; 
		Node.adjNode.add(node1);
		Node.numOfEdges=(Node.numOfEdges)+1; 
		i=i+1;
		Deg=Deg+2;   
                //Number of Nodes
		int n_Nodes=nodeList.size();
                
		//Calculating the Probability
		for(int k = 0 ; k < nodeList.size(); k++){
			BProb.add((nodeList.get(k).numOfEdges)/(Deg));
			DProb.add((n_Nodes-(nodeList.get(k).numOfEdges))/((n_Nodes*n_Nodes)-(Deg)));
		}
	}
       
        
        //Death Method
        @SuppressWarnings("static-access")
	public static void Deathnode(Node NodeSelected){
		int pos=nodeList.indexOf(NodeSelected);
		nodeList.remove(NodeSelected);
		Deg=Deg-(Node.adjNode).size();
		int length=nodeList.size();
		if(pos > 0){
			for(int j = 0; j < length ; j++){
				nodeList.get(j);
				if(Node.adjNode.contains(NodeSelected)){
					nodeList.get(j);
					Node.adjNode.remove(NodeSelected);
					nodeList.get(j);
					Node.numOfEdges=(nodeList.get(j).numOfEdges)-1;
					Deg=Deg-1;
				}
			}
			int numOfNodes=len(nodeList);
			if (numOfNodes==1){
				DProb.add(1);
				BProb.add(1);
			}else{
				//Calculating the Probability of all nodes again
				for(int k = 0 ; k < len(nodeList);k++){
					BProb.add((nodeList.get(k).numOfEdges)/(Deg));
					DProb.add((numOfNodes-(nodeList.get(k).numOfEdges))/((numOfNodes*numOfNodes)-(Deg)));
				}
			}

		}
	}
        
     //Implemented as presented by Mihhail Verhovtsov (2015):[Commit 77735df]
     // https://github.com/Infeligo/jgrapht-metrics/blob/master/src/main/java/org/jgrapht/metrics/AssortativityCoefficientMetric.java
        
	public static double AssortivityCoefficient(ArrayList<Node> list) {

		double EdgeCounter = 0;
		for(Node n : list) {
			EdgeCounter += n.getNumOfEdges();
		}
		Random r = new Random();
                
                double FirstFactor = 0;
		double SecondFactor = 0;
		double ThirdFactor = 0;
                double result=0;

		

		for(int i = 0 ; i < list.size(); i++) {
			Node n = list.get(i);
			for(int j = 0 ; j < n.getNumOfEdges(); j++) {
				if(i != j) {
					int d1 = n.getNumOfEdges() * r.nextInt(10);
					int d2 = n.getAdjacentnode().get(j).getNumOfEdges() * r.nextInt(10);
					FirstFactor += d1 * d2;
					SecondFactor += d1 + d2;
					ThirdFactor += d1*d1+d2*d2;
				}
			}
		}
		
		FirstFactor /= EdgeCounter;
		SecondFactor = (SecondFactor / (2 * EdgeCounter)) * (SecondFactor / (2 * EdgeCounter));
		ThirdFactor /= (2 * EdgeCounter);
                result=(FirstFactor - SecondFactor) / (ThirdFactor - SecondFactor);
		return result;
	}

	


	//Actual main method to run
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
                //
                ArrayList<Double> li = new ArrayList<Double>();

		
                int maxSteps = 20000;
                int startSteps=10000;
		
	nodeList = new ArrayList<>();	
        BProb = new ArrayList<>();
	DProb = new ArrayList<>();
                
		System.out.println("Start");
                
		Start();

		//First Run
            ArrayList<Integer> numOfSteps= new ArrayList<>();
            ArrayList<Integer> numOfNodes= new ArrayList<>();
            ArrayList<Integer> numOfEdges= new ArrayList<>();
		
		for(int j = startSteps ; j < maxSteps ; j++){
			double x=rand.nextInt(10);
			x=x/10;
                        //p=0.6
			if(x <= 0.6){
				CBProb.removeAll(CBProb);
				Node NodeSelected=CumProb();
				Birthnode(NodeSelected);
			}else{
				int maxVal = Collections.max(DProb);
				int maxpos = DProb.indexOf(maxVal);
				Node NodeSelected=nodeList.get(maxpos);
				Deathnode(NodeSelected);
				int length=len(nodeList);
				if(length == 0){
					Start();
				}
			}
                    numOfSteps.add(j);
		    numOfNodes.add(len(nodeList));
		    numOfEdges.add(Deg);
		}
                
		System.out.println("Done with First Run");
		li.add(AssortivityCoefficient(nodeList));
		System.out.println("Done With Calculation");
                
		BProb = new ArrayList<>();
		DProb = new ArrayList<>();
		nodeList = new ArrayList<>();

		//Second Run
	Start();
        
            ArrayList<Integer> numOfSteps2= new ArrayList<>();
            ArrayList<Integer> numOfNodes2= new ArrayList<>();
            ArrayList<Integer> numOfEdges2= new ArrayList<>();		
		
		for(int j = startSteps ; j < maxSteps ; j++){
			double x=rand.nextInt(10);
			x=x/10;
                        //p=0.75
			if(x <= 0.75){
				CBProb.removeAll(CBProb);
				Node NodeSelected=CumProb();
				Birthnode(NodeSelected);
			}else{
				int maxVal = Collections.max(DProb);
				int maxpos = DProb.indexOf(maxVal);
				Node NodeSelected=nodeList.get(maxpos);
				Deathnode(NodeSelected);
				int length=len(nodeList);
				if(length == 0){
					Start();
				}
			}
                    numOfSteps2.add(j);
		    numOfNodes2.add(len(nodeList));
		    numOfEdges2.add(Deg);
		}
                
		System.out.println("Done with Second Run");


		li.add(AssortivityCoefficient(nodeList));

		BProb = new ArrayList<>();
		DProb = new ArrayList<>();
		nodeList = new ArrayList<>();

		//Third Run
	Start();
        
            ArrayList<Integer> numOfSteps3= new ArrayList<>();
            ArrayList<Integer> numOfNodes3= new ArrayList<>();
            ArrayList<Integer> numOfEdges3= new ArrayList<>();
		
		for(int j = startSteps ; j < maxSteps ; j++){
			double x=rand.nextInt(10);
			x=x/10;
                        //p=0.9
			if(x <= 0.9){
				CBProb.removeAll(CBProb);
				Node NodeSelected=CumProb();
				Birthnode(NodeSelected);
			}else{
				int maxVal = Collections.max(DProb);
				int maxpos = DProb.indexOf(maxVal);
				Node NodeSelected=nodeList.get(maxpos);
				Deathnode(NodeSelected);
				int length=len(nodeList);
				if(length == 0){
					Start();
				}
			}
                    numOfSteps3.add(j);
		    numOfNodes3.add(len(nodeList));
		    numOfEdges3.add(Deg);
		}
		
                System.out.println("Done with Third Run");
		
		li.add(AssortivityCoefficient(nodeList));
		ArrayList<Integer> runs = new ArrayList<Integer>();
		for(int i = 0; i < 3;i++) {
			runs.add(i+1);
		}
		System.out.println(li);
		System.out.println("Graph Plotting");
		LineChart A_Graph = new LineChart("Assortativity Coefficient Metric");
		A_Graph.LineChart1("Coefficient Value","Test ",runs,li,"Coefficient Value");
		A_Graph.pack();
		RefineryUtilities.centerFrameOnScreen( A_Graph );          
		A_Graph.setVisible( true );

	}
}
