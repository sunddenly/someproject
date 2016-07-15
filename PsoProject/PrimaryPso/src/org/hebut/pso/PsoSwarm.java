package org.hebut.pso;

import java.util.ArrayList;
//import java.lang.Math.*;

import uchicago.src.sim.util.Random;

public class PsoSwarm {
// ---------------------------------------------------------------- Members
	protected ArrayList agentList;
	private double[] gBest;
	private double gBestValue;
	private int swarmIndex;
	private int dimension;
	private int numParticles;
	private double rightRange;
	private double leftRange; 
 	public PsoSwarm() {
	}

	public PsoSwarm(int index, int dim) {
	  this.dimension = dim;
	  this.numParticles = 4;
	  this.swarmIndex = index;
	  // Create an array list contains all agents;
	  agentList = new ArrayList();  
	  
	  // Initalize the arrays
	  gBest = new double[dimension];
	  gBestValue = 1.7976931348623157E308;   
  
	  // Obtain right range and left range from PSOParameters object
	  rightRange = 30;
	  leftRange = -30;
  
	  for(int k=0; k<dimension; k++){
	      gBest[k] = (rightRange - leftRange) * getRandomUniform()+ leftRange;
	  }
  
	  // Create a population for the swarm
	  for (int i=0; i < numParticles; i++) {
	      PsoAgent agent = new PsoAgent(this);
	      // Add this agent into the agent list
	      agentList.add(agent);       
	  }
}

	public double[] getRandomPresent() {
	  int randSelect;    
	  randSelect = (int)(Math.floor(Math.random() * numParticles));  //select a random individual
	  PsoAgent agent = (PsoAgent) agentList.get(randSelect); 
	  return agent.getPresent();
	
	}

	public double getLeftRange() {
	  return leftRange;
	}
	
	public void setLeftRange(double lr) {
	  leftRange = lr;
	}

	public double getRightRange() {
	  return rightRange;
	}
	
	public void setRightRange(double rr) {
	  rightRange = rr;
}

	public int getDimension() {
	    return dimension;
	}

	public void setDimension(int dim) {
	    dimension = dim;
	}

	public ArrayList getAgentList() {
		return agentList;
	}

	public double[] getGBest() {
	  return gBest;
	}

	public void setGBest(double[] gb) {
	  for (int i=0; i < dimension; i++) 
	       gBest[i] = gb[i];
	}
	
	public double getGBestValue() {
	  return gBestValue;
	}

	public void setGBestValue(double gb) {
	  gBestValue = gb;
	  //System.out.println("gBestValue: "+gBestValue);
	}
	
	public double getRandomUniform() {
	    return Math.random();
	}
}

