
/**
 * <p>Title: PSOSwarm.java</p>
 * <p>Description: Represent a swarm for a CCPSO2 </p>
 * <p>Copyright: Copyright (c) 9/4/2009</p>
 * <p>Company: RMIT CS</p>
 * @author Xiaodong Li (xiaodong.li@rmit.edu.au)
 * @version 1.0
 */

import java.util.ArrayList;
//import java.lang.Math.*;


// If an agent, or any other object is to be displayed in a display it
// needs to implement at least the drawable interface which has one method:
// draw(SimGraphics g)
public class PSOSwarm {

  // ---------------------------------------------------------------- Members

  /** Two coordindates (x, y) represent this agent on the 2-dimensional lattice **/
  protected int x, y;

  /** A list of all existing agents **/
  protected ArrayList agentList;
  
  /** An array represents the best location found so far from the swarm **/
  private double[] gBest;
 
  /** The best fitness value in history of this agent **/
  private double gBestValue;
  
  /** The array stores the location of the neighbourhood best **/
  private double[] nBest;

  /** The value of the local best **/
  private double nBestValue = 1.7976931348623157E308;

  /** A list of all existing nBest **/
  protected ArrayList nBestList;

  /** The model associated with this agent **/
  private PSOModel model;
    
  /** Agent index for drawing purpise **/
  private int swarmIndex;
  
  /** The dimension of the swarm (ie., number of variables) **/
  private int dimension;
  
  /** The number of particles for each swarm **/
  private int numParticles;
  
  /** Number of evaluations **/
  protected int numEvals = 0;
  
  /** left and right ranges of the variables **/
  private double rightRange;
  private double leftRange;
  
  public String method; 
  
  public PSOSwarm() {

  }

  public PSOSwarm(PSOModel model, int si, int dim) {
    // Copy the space and model
    this.model = model;
    this.dimension = dim;
    this.numParticles = model.getNumParticles();
    this.swarmIndex = si;
    this.method = model.getMethod();
    
    // Create an array list contains all agents;
    agentList = new ArrayList();  
    
    // Initalize the arrays
    gBest = new double[dimension];
    gBestValue = 1.7976931348623157E308;   
        
    // Initialize the nBestList
    nBestList = new ArrayList();

    // Initialize the nBest[] and nBest value
    nBest = new double[dimension];
    nBestValue = 1.7976931348623157E308;
    
    // Obtain right range and left range from PSOParameters object
    rightRange = model.getRightRange();
    leftRange = model.getLeftRange();
    
    for(int k=0; k<dimension; k++)
    {
        gBest[k] = (rightRange - leftRange) * getRandomUniform() + leftRange;
        nBest[k] = gBest[k];
    }
    
    // Create a population for the swarm
    for (int i=0; i < numParticles; i++) 
    {
        PSOAgent agent = new PSOAgent(this);
        // Add this agent into the agent list
        agentList.add(agent);       
    }
  }


 /**
   * step() is called by the scheduler once per tick.
   */
  public void step() {
  
  }
 
 
  public double[] getRandomPresent() {
    int randSelect;    
    randSelect = (int)(Math.floor(Math.random() * numParticles));  //select a random individual
    PSOAgent agent = (PSOAgent) agentList.get(randSelect); 
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
 
  public int getNumEvals() {
       return numEvals;
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

  public double getVelocityMax() {
    return model.getVelocityMax();
  }

  public void setVelocityMax(double v) {
    model.setVelocityMax(v);
  }
  
  public double getRandomUniform() {
    return model.getRandomUniform();
  }

  public double getGaussian() {
    return model.getGaussian();
  } 
  
  public double getCauchy() {
    return model.getCauchy();
  } 
  
  public double getTicks() {
    return model.getTicks();
  } 
 
  public void setTicks(int ts) {
     model.setTicks(ts);
  } 
 
  public String getMethod() {
    return method;
  }

  public void setMethod(String m) {
     method = m;
  }
  
  // setting nBests for all particles in the swarm (of a ring topology)
  public void setAllNBest() 
  {
    // Iterate through the list of agents to decide nBest
    int agentListSize = agentList.size();
    for (int i = 0; i < agentListSize; i++) 
    {
        PSOAgent agent = (PSOAgent) agentList.get(i);
    
        // Obtain its pBest fitness value 
        double pBestValue = agent.getPBestValue();

        // Update the nBest value
        nBestValue = pBestValue; 
        setNBest(agent.getPBest());           
                  
        PSOAgent agent2 = new PSOAgent(this);
        if (i < (agentListSize - 1))
            agent2 = (PSOAgent) agentList.get(i+1);
        else if (i >= (agentListSize - 1))
            agent2 = (PSOAgent) agentList.get(0); // connect back to the first one
 
        PSOAgent agent3 = new PSOAgent(this);
        if (i < 1)
            agent3 = (PSOAgent) agentList.get(agentListSize - 1);
        else if (i <= (agentListSize - 1))
            agent3 = (PSOAgent) agentList.get(i-1);
           
        double pBestValue2 = agent2.getPBestValue();
        
        // Choosing the local leader for agent 1, 2 and 3
        if (pBestValue2 < nBestValue)
        {
            // Update the nBest value
            nBestValue = pBestValue2;
            // Update the nBest location
            setNBest(agent2.getPBest());   
        }
   
        double pBestValue3 = agent3.getPBestValue();
        
        // Choosing the local leader for agent 1, 2 and 3
        if (pBestValue3 < nBestValue)
        {
            // Update the nBest value
            nBestValue = pBestValue3;            
            // Update the nBest location
            setNBest(agent3.getPBest());   
        }
           
        // set the local leader for the particle and its immediate neighbours
        agent.setNBest(nBest);
        agent.setNBestValue(nBestValue);
       
        //System.out.println("agent["+i+"]: "+agent.getPBestValue());
        //System.out.println("agent2: "+agent2.getPBestValue());
        //System.out.println("agent3: "+agent3.getPBestValue());
    }   
  
  } 
 
  private void setNBest(double[] nb) {
    for (int i=0; i < dimension; i++) 
        nBest[i] = nb[i];
  }

}
