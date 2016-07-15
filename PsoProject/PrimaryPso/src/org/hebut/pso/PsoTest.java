package org.hebut.pso;

import java.util.ArrayList;
import java.util.Random;

public class PsoTest {
	/* parameters accessor methods */
	private int numParticles = 4;
	private int numSwarm = 2;
	private int dimension = 4;
	private int swarmDim = dimension/numSwarm;
	private double[] present;
	protected ArrayList swarmList; 
	private double[] randomPresent;  
	private double fitnessValue;
	private double randomPresentFitValue;
	private double gBestValue;
	private double[] gBest;  
	
	private double[][] pop;
	private double[][] popPBest;  
	private double[][] velocity; 
	
	private int step=1;
	
	public void initlisze(){
		swarmList = new ArrayList();         	    
		// gBest[] and gBest value
		gBest = new double[dimension];
		present = new double[dimension];
		randomPresent = new double[dimension];
			
		pop = new double[numParticles][dimension];
		popPBest = new double[numParticles][dimension];
		velocity = new double[numParticles][dimension];   
		                
		gBestValue = 1.7976931348623157E308;
		fitnessValue = 1.7976931348623157E308;
		randomPresentFitValue = 1.7976931348623157E308;	
		
		for (int i = 0; i < numSwarm; i++) {   
	        PsoSwarm swarm = new PsoSwarm(i, swarmDim);
	        swarmList.add(swarm);       
	    }
		CreateSwarmList();
		//out();
		//out1();
		calculate();
	}
	public void calculate(){
		for (int m = 0; m < 2; m++) {
	        PsoSwarm swarm = (PsoSwarm) swarmList.get(m);      
	        ArrayList agentList = swarm.getAgentList();
	        
	        for(int i=0; i < agentList.size(); i++){
	        	PsoAgent agent = (PsoAgent) agentList.get(i);
	        	setPresent(agent.getPresent(), m);
	        	for (int n = 0; n < numSwarm; n++){
                    PsoSwarm swarm2 = (PsoSwarm)swarmList.get(n);
                    if (m != n){
                        if (step == 1)
                            setPresent(swarm2.getRandomPresent(), n);
                        else
                            setPresent(swarm2.getGBest(), n);
                    }
                }
	        	/*System.out.println("agent["+i+"]");
	        	for(int j=0;j<present.length;j++)
		        	System.out.print(present[j]+" ");
		        System.out.println();
		        fitnessValue=calculateFitness(present);
		        System.out.println(fitnessValue);*/
		        
	        	fitnessValue=calculateFitness(present);
	        	agent.setFitnessValue(fitnessValue);
	        	agent.UpdatePBest();
	        	
	        	double pBestValue = agent.getPBestValue();
	        	double swarmGBestValue = swarm.getGBestValue();
                //System.out.println("pBestValue: "+pBestValue);         
                if (pBestValue < swarmGBestValue){
                    swarm.setGBestValue(pBestValue);
                    swarm.setGBest(agent.getPBest());
                    //System.out.println("update swarm's gBest");
                }
	        }
	        System.out.println("swarm:["+m+"]swarmGBestValue "+swarm.getGBestValue());
	       /* gBest=swarm.getGBest();
	        for(int i=0;i<gBest.length;i++)
	        	System.out.print(gBest[i]);
	        System.out.println();*/
	        double swarmGBestValue = swarm.getGBestValue();            
            if (swarmGBestValue < gBestValue){
                gBestValue = swarmGBestValue; 
                for (int n = 0; n < numSwarm; n++){
                    PsoSwarm swarm2 = (PsoSwarm)swarmList.get(n);
                    setPresent(swarm2.getGBest(), n);
                }
                setGBest(present);
            }
		}
		for (int m = 0; m < numSwarm; m++){
            PsoSwarm swarm = (PsoSwarm)swarmList.get(m);
            ArrayList agentList1 = swarm.getAgentList();
            
            for (int i = 0; i < agentList1.size(); i++){
                PsoAgent agent = (PsoAgent)agentList1.get(i);

                // set gBest for each particle in each swarm
                agent.setGBestValue(swarm.getGBestValue());
                agent.setGBest(swarm.getGBest());

                agent.update();
                //update the pop and v for the agent
                updatePopRow(agent.getPresent(), pop, m, i);
                updatePopPBestRow(agent.getPBest(), popPBest, m, i);
                updateVRow(agent.getVelocity(), velocity, m, i);
            }
        }

		System.out.println("GbestValue:"+gBestValue);
		for(int i=0;i<gBest.length;i++)
        	System.out.print(gBest[i]+" ");
	}
	public void CreateSwarmList(){		
		// update pop and v.
	    for (int m = 0; m < numSwarm; m++) {
	        PsoSwarm swarm = (PsoSwarm) swarmList.get(m);      
	        ArrayList agentList1 = swarm.getAgentList();
	        
	        for(int i=0; i < agentList1.size(); i++){
	            PsoAgent agent = (PsoAgent) agentList1.get(i);
	            // first need to save the agent back to a row of pop array
	            // then we need to reload it again, before doing the shuffling
	            updatePopRow(agent.getPresent(),pop,m,i);
	            updatePopPBestRow(agent.getPBest(),popPBest,m,i);
	            updateVRow(agent.getVelocity(),velocity,m,i);
	        }  
	    }
	    
	}
	
	
	
	public double calculateFitness(double[] present) {
		   // Calculate fitness value
		   double fitValue = 1.7976931348623157E308;  
		   fitValue = sphere(present);    
		   return fitValue;
	}	
	
	public void out(){
		for(int i=0;i<numParticles;i++){
			System.out.print("pop["+i+"] ");
			for(int j=0;j<dimension;j++)
				System.out.print(pop[i][j]+" ");
			System.out.println();
		}
		for(int i=0;i<numParticles;i++){
			System.out.print("popPBest["+i+"] ");
			for(int j=0;j<dimension;j++)
				System.out.print(popPBest[i][j]+" ");
			System.out.println();
		}
		for(int i=0;i<numParticles;i++){
			System.out.print("velocity["+i+"] ");
			for(int j=0;j<dimension;j++)
				System.out.print(velocity[i][j]+" ");
			System.out.println();
		}		
	}
	public void out1(){
		for (int m = 0; m < 2; m++) {
	        PsoSwarm swarm = (PsoSwarm) swarmList.get(m);      
	        ArrayList agentList = swarm.getAgentList();
	        
	        for(int i=0; i < agentList.size(); i++){
	        	PsoAgent agent = (PsoAgent) agentList.get(i);
	        	setPresent(agent.getPresent(), m);
	        	System.out.println("swarm["+m+"]"+i);
		        for(int j=0;j<present.length;j++)
		        	System.out.print(present[j]+" ");
		        System.out.println();
	        }	        
		}		
	}
	
	public static void main(String[] args) {
		
		PsoTest pso=new PsoTest();
		pso.initlisze();
		
		
	}
	
	public void setGBest(double[] gb) {
	    for (int j=0; j < dimension; j++)
	        gBest[j] = gb[j];
	}
	public double[] getGBest() {
	    return gBest;
	  }	
	
	public  void setPresent(double[] p, int m) {
	    for (int j=0; j < swarmDim; j++){
	       present[m*swarmDim+j]= p[j];      
	    }
	}
	
	public double sphere(double[] present) {
	    double total = 0;
	    for (int i=0; i < dimension; i++) {
	      total += ((present[i]) * (present[i]));
	    }
	    return total; //minimization
	}
	
	public void updatePopRow(double[] p, double[][] pop, int j, int i) {	    
	    int lB = j*swarmDim;
	    for (int k = 0; k < swarmDim; k++){
	        pop[i][lB+k] = p[k];
	        //System.out.println("**pop["+i+"]["+(lB+k)+"]: "+pop[i][lB+k]);
	    }
	}	  
	public void updatePopPBestRow(double[] p, double[][] popPBest, int j, int i) {	    
	    int lB = j*swarmDim;
	    for (int k = 0; k < swarmDim; k++){
	        popPBest[i][lB+k] = p[k];
	        //System.out.println("**popPBest["+i+"]["+(lB+k)+"]: "+popPBest[i][lB+k]);
	    }
	}  
	public void updateVRow(double[] vel, double[][] v, int j, int i) {	    
	    int lB = j*swarmDim;
	    for (int k = 0; k < swarmDim; k++){
	        v[i][lB+k] = vel[k];
	        //System.out.println("**v["+i+"]["+(lB+k)+"]: "+v[i][lB+k]);
	    }
	}

}
