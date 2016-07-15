package org.hebut.pso;
public class PsoAgent {
	// ---------------------------------------------------------------- Members
	private double[] present;
	private double[] velocity;
	private double[] pBest;
	private double[] gBest;
	
	private double fitnessValue=1.7976931348623157E308;
	private double pBestValue=1.7976931348623157E308;
	private double gBestValue=1.7976931348623157E308; 
	  
	private PsoSwarm swarm;
	private int dimension;  
	private double rightRange;
	private double leftRange; 
	//----------------------------------------------------------------- Constructors
	public PsoAgent() {
	}
	public PsoAgent(PsoSwarm swarm) {
	    this.swarm = swarm;
	    this.dimension = swarm.getDimension();
	    
	    // Initalize the arrays
	    present = new double[dimension];
	    pBest = new double[dimension];
	    gBest = new double[dimension];
	    velocity = new double[dimension];  
	    
	    // Initalize the value
	    pBestValue = 1.7976931348623157E308;    
	     	       
	    // Obtain right range and left range from PSOParameters object
	    rightRange = swarm.getRightRange();
	    leftRange = swarm.getLeftRange();
	 
	    // Randomly initialize the velocity and pbest
	    for (int i=0; i < dimension; i++) {
	         present[i] = (rightRange - leftRange) * swarm.getRandomUniform() + leftRange;
	         pBest[i] = present[i];    
	         gBest[i] = present[i];  

	         velocity[i] = (rightRange - leftRange) * swarm.getRandomUniform() + leftRange;
	       
	         // 50% of v[i] are in an opposite direction
	         if (swarm.getRandomUniform() <= 0.5)
	        	 velocity[i] = -velocity[i];
	         //System.out.println("present["+i+"]"+present[i]);
	     }
	  }
	
	public double getFitnessValue() {
	       //System.out.println("present[0] :"+present[0]+" fitnessValue*** :"+fitnessValue);  
	       return fitnessValue;
	   }
	public void setFitnessValue(double fv) {
		fitnessValue = fv;
	}
	
	public double getPBestValue() {
	    return pBestValue;
	}
	public void setPBestValue(double pbv) {
	    pBestValue = pbv;
	}
	
	public double[] getPBest() {
	    return pBest;
	}	   
	public void setPBest(double[] pb) {
		   for (int j=0; j < dimension; j++)
		      pBest[j] = pb[j];
	}
	
	public double[] getPresent() {
	    return present;
	}
	public void setPresent(double[] sp) {    
	   for (int j=0; j < dimension; j++){
	        present[j] = sp[j];
	        //System.out.println("presentChanged["+j+"]"+present[j]);
	   }
	}
   
	public double[] getVelocity() {
	    return velocity;
	}
	public void setVelocity(double[] vel) {    
	   for (int j=0; j < dimension; j++){
	        velocity[j] = vel[j];
	        //System.out.println("presentChanged["+j+"]"+present[j]);
	   }
	}   

	public void setGBestValue(double gb) {
		gBestValue = gb;
		//System.out.println("gBestValue: "+gBestValue);
	}
	
	public void setGBest(double[] gb) {
	       for (int j=0; j < dimension; j++)
	        gBest[j] = gb[j];
	}
	
	public void UpdatePBest(){ 	    
	    // update the pBest
	    if (fitnessValue < pBestValue) {
	       pBestValue = fitnessValue;
	       for (int i=0; i < present.length; i++) 
	          pBest[i] = present[i];
	       //System.out.println("pBest updated");
	    }   
	}
	    
	protected void update() {	     
	   // update particle positions according to the constriction PSO rules.
	   for (int k=0; k < dimension; k++) {            
	       velocity[k] = 0.729843788*(velocity[k]
	                + 2.05 * swarm.getRandomUniform() * (pBest[k] - present[k]) 
	                + 2.05 * swarm.getRandomUniform() * (gBest[k] - present[k])); 
	       present[k] = present[k] + velocity[k];
	   }
	}
	
}
