
/**
 * <p>Title: PSOAgent.java</p>
 * <p>Description: Represent a particle in a swarm</p>
 * <p>Copyright: Copyright (c) 20/3/2009</p>
 * <p>Company: RMIT CS</p>
 * @author Xiaodong Li (xiaodong.li@rmit.edu.au)
 * @version 1.0
 */


// If an agent, or any other object is to be displayed in a display it
// needs to implement at least the drawable interface which has one method:
// draw(SimGraphics g)
public class PSOAgent {

  // ---------------------------------------------------------------- Members
  /** An array contains the value for each dimension of the current position of the
   * agent
   */
  private double[] present;

  /** An array represents the best location so far of the agent **/
  private double[] pBest;

  /** An array represents the best location so far of the agent **/
  private double[] gBest;

  /** An array represents the neighbourhood best location so far of the agent **/
  private double[] nBest;
  
  /** An array represent the current velocity vector of the agent **/
  private double[] v;

  /** The current fitness value **/
  private double fitnessValue;

  /** The best fitness value in history of this agent **/
  private double pBestValue;
  
  /** The best fitness value in history of this agent **/
  private double gBestValue;
  
  /** The local neighbourhood best fitness value in history of this agent **/
  private double nBestValue;

  /** The swarm associated with this agent **/
  private PSOSwarm swarm;
  
  /** Dimensions (ie., the number of variables) **/
  private int dimension;  
    
  /** left and right ranges of the variables **/
  private double rightRange;
  private double leftRange;

  /** count the number of consequtive improvement **/
  private int countsImproved;  

  /** scaling factor if the number of consequtive improvements exceed a specified number **/
  private double scale = 0.5;  
  
  
  public PSOAgent() {

  }

  public PSOAgent(PSOSwarm swarm) {
    // Copy the space and model
    this.swarm = swarm;
    this.dimension = swarm.getDimension();
    // Initalize the arrays
    present = new double[dimension];
    pBest = new double[dimension];
    gBest = new double[dimension];
    nBest = new double[dimension];
    pBestValue = 1.7976931348623157E308;  
    gBestValue = 1.7976931348623157E308;  
    nBestValue = 1.7976931348623157E308;   
    v = new double[dimension];     
       
    // Obtain right range and left range from PSOParameters object
    rightRange = swarm.getRightRange();
    leftRange = swarm.getLeftRange();
 
    // Randomly initialize the velocity and pbest
    for (int i=0; i < dimension; i++) 
    {
         present[i] = (rightRange - leftRange) * swarm.getRandomUniform() + leftRange;
         pBest[i] = present[i];    
         gBest[i] = present[i]; 
         nBest[i] = present[i]; 

         v[i] = (rightRange - leftRange) * swarm.getRandomUniform() + leftRange;
       
         // 50% of v[i] are in an opposite direction
         if (swarm.getRandomUniform() <= 0.5)
            v[i] = -v[i];
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

   public double[] getPresent() {
       return present;
   }
   
   
   public double[] getPBest() {
       return pBest;
   }
   
   public void setGBest(double[] gb) {
       for (int j=0; j < dimension; j++)
        gBest[j] = gb[j];
   }


   public void setGBestValue(double gbv) {
       gBestValue = gbv;
   }

   public void setPresent(double[] sp) {    
      for (int j=0; j < dimension; j++)
      {
        present[j] = sp[j];
        //System.out.println("presentChanged["+j+"]"+present[j]);
      }
   }

   public void setPBest(double[] pb) {
       for (int j=0; j < dimension; j++)
        pBest[j] = pb[j];
   }
   
   public void setPBestValue(double pbv) {
       pBestValue = pbv;
   }
   
   public double[] getVelocity() {
       return v;
   }
 
   public void setVelocity(double[] vel) {    
      for (int j=0; j < dimension; j++)
      {
        v[j] = vel[j];
        //System.out.println("presentChanged["+j+"]"+present[j]);
      }
   }
   
   public void setNBest(double[] nb) {
       for (int j=0; j < dimension; j++)
        nBest[j] = nb[j];
   }

   public double getNBestValue() {
       return nBestValue;
   }

   public void setNBestValue(double gbv) {
       nBestValue = gbv;
   }

  /**
   * step() is called by the scheduler once per tick.
   */
  public void step() {
     
    // If this is the first iteration, then pbestValue is assigned to the current value
    if (swarm.getTicks() == 1) {
        pBestValue = fitnessValue;
    }
    
    // update the pBest
    if (fitnessValue < pBestValue) {
       pBestValue = fitnessValue;
       for (int i=0; i < present.length; i++) 
          pBest[i] = present[i];
       //System.out.println("pBest updated");
    }   
    
 }
  
 
 
 
 /**
   * Update the current position of each agent in the popoluation
   * using the standard constriction PSO)
   */
 /**
   * This step() method implements the PSO algorithm as stated below
   *
   * (a):  v[] = ki * (v[] + c1 * rand() * (pBest[] - present[])
   *               + c2 * rand() * (gBest[] - present[]))
   * (b):   present[] = persent[] + v[]
   *
   *    Do
   *      For each particle
   *      Calculate fitness value
   *        If the fitness value is better than the best fitness value (pBest) in history
   *        set current value as the new pBest
   *   End
   *
   *   Choose the particle with the best fitness value of all the particles as the gBest
   *
   *   For each particle
   *        Calculate particle velocity according equation (a)
   *        Update particle position according equation (b)
   *   End
   *  While maximum iterations or minimum error criteria is not attained
   *
   */
protected void update() {
     
   // update particle positions according to the constriction PSO rules.
   for (int k=0; k < dimension; k++) 
   {
         if (swarm.getMethod().equals("CCPSO_Ring")) 
         {              
            v[k] = 0.729843788*(v[k]
                + 2.05 * swarm.getRandomUniform() * (pBest[k] - present[k]) 
                + 2.05 * swarm.getRandomUniform() * (nBest[k] - present[k]));
            
         } 
         else if (swarm.getMethod().equals("CCPSO_Sync")) 
         {              
            v[k] = 0.729843788*(v[k]
                + 2.05 * swarm.getRandomUniform() * (pBest[k] - present[k]) 
                + 2.05 * swarm.getRandomUniform() * (gBest[k] - present[k]));
         }
         else if (swarm.getMethod().equals("CCPSO_Barebone"))
         {
            present[k] = (pBest[k]+nBest[k])/2.0 + swarm.getGaussian() * Math.abs(pBest[k] - nBest[k]);
         }   
         else if (swarm.getMethod().equals("CCPSO_Gaussian"))
         {
           if (swarm.getRandomUniform() < 0.5)
               present[k] = pBest[k] + swarm.getGaussian() * Math.abs(pBest[k] - gBest[k])/2.0;     
           else    
               present[k] = gBest[k] + swarm.getGaussian() * Math.abs(pBest[k] - gBest[k])/2.0;              
         }  
         else if (swarm.getMethod().equals("CCPSO_Cauchy"))
         {
           if (swarm.getRandomUniform() < 0.5)
               present[k] = pBest[k] + swarm.getCauchy() * Math.abs(pBest[k] - nBest[k])/2.0;     
           else    
               present[k] = nBest[k] + swarm.getGaussian() * Math.abs(pBest[k] - nBest[k])/2.0;              
         }  
        
         // Adjust the velocity vector so that the agent cannot go
         // beyond the search space, preventing the population from explosion
         if (v[k] > swarm.getVelocityMax()) 
              v[k] = swarm.getVelocityMax();
         else if (v[k] < -swarm.getVelocityMax()) 
              v[k] = - swarm.getVelocityMax();
         
         // Based on the new velocity, update the agent's position
         if (swarm.getMethod().equals("CCPSO_Sync") || swarm.getMethod().equals("CCPSO_Ring")) 
            present[k] = present[k] + v[k];
                  
         // if present[k] exceeds the upper or lower boundary, it is mirrored back.                         
         if (present[k] > rightRange) 
            present[k] = 2*rightRange - present[k];
         else if (present[k] < leftRange) 
            present[k] = 2*leftRange - present[k];
         
         //System.out.println("present["+k+"]:: "+present[k]);          
   }//for loop1
}
  
}
