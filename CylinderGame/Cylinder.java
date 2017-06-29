

public class Cylinder {
   private final double radius;    // doesn't change after Cylinder is constructed   
   private final double height; // doesn't change after Cylinder is constructed

   private double waterHeight;  
   
    /*
     * Constructor 
     * Initially waterHeight = 0
     * 
     * If any value is less than or equal to 0, 
     * set radius and height to 1 and print "ERROR Cylinder"
     */
    public Cylinder(double radius, double height){
     
    }
    
    // Constructor
	// set waterHeight to 0
	// set radius and height to 1
    public Cylinder(){

    }
    
    // getRadius: returns the radius of the Cylinder
    
    // getHeight: returns the height of the Cylinder
    
    // getVolume: returns the total volume of the cylinder (divided by PI)

    // getWaterVolume: returns the volume of water in the cylinder (divided by PI)
    
	// toString: returns a String describing the cylinder
	//   for example, for the Cylinder with radius=10, height=12, waterHeight=4
	//   the String will be "Cylinder(volume=1200pi, waterVolume=400pi)",
	// that is the total volume and current water volume
    public String toString() {
		final String pi = "\u03c0";
        return "Cylinder(volume=" + getVolume() + pi + ", waterVolume=" + getWaterVolume() + pi + ")"; 
        
    }

    // pourWaterFrom
	// Move water from other into this Cylinder.
	// If you would overflow this Cylinder, *only* move the volume
	// of water that would fill this Cylinder to the top.
    public void pourWaterFrom(Cylinder other){

    }

	// completely fills this Cylinder with water
	public void fillToTop() {
		waterHeight = height;	
	}
}
