
/**
 * <p>Title: DimIndex.java</p>
 * <p>Description: Represent index and rank for one dimension</p>
 * <p>Copyright: Copyright (c) 30/4/2009</p>
 * <p>Company: RMIT CS</p>
 * @author Xiaodong Li (xiaodong.li@rmit.edu.au)
 * @version 1.0
 */


// If an agent, or any other object is to be displayed in a display it
// needs to implement at least the drawable interface which has one method:
// draw(SimGraphics g)
public class DimIndex {

  // ---------------------------------------------------------------- Members 
  /** A difference before gBest and prevGBest at a changed dimIndex (e.g., based on ranking) for a dimension **/
  private double delta;

  /** The original dimensional index for a test function **/
  private int orgIndex; 
  
  private int updatedIndex; 
    
  public DimIndex() {

  }

  public DimIndex(int in, double d) {  
    orgIndex = in;
    updatedIndex = orgIndex;
    delta = d;
  }
  
  public void setOrgIndex(int in) {
        orgIndex = in;
  }
  
  public int getOrgIndex() {
       return orgIndex;
  }
  
  public void setUpdatedIndex(int upin) {
        updatedIndex = upin;
  }
  
  public int getUpdatedIndex() {
       return updatedIndex;
  }
  
  public void setDelta(double d) {
       delta = d;
  }
  
  public double getDelta() {
       return delta;
  }
}
