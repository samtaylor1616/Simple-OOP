/* ****************************** *
 * Filename: Submarine.java       *
 * Author: Samantha Taylor        *
 * Date last modified: 26/05/2019 *
 * ****************************** */

 public class Submarine extends Ship {
    //Class Constant
    public static final String[] validHulls = {"STEEL", "ALLOY", "TITANIUM"};
    public static final double MIN_DEPTH = 0.0;
    public static final double MAX_DEPTH = -500.0;

    //Classfields
    private String hull;
    private double maxDepth;

// ***************************** CONSTRUCTORS ******************************** //
    // Default Constructor
    public Submarine() {
        super();
        setHull(validHulls[0]);
        setMaxDepth(MIN_DEPTH);
    }

    // Alternate Constructor
    public Submarine(Engine engine, String hull, String serialNum, double maxDepth, int year) {
        super(engine, serialNum, year);
        try {
            setHull(hull);
            setMaxDepth(maxDepth);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Error constructing Submarine: " 
                                                + e.getMessage());
        }
    }

    // Copy Constructor
    public Submarine(Submarine inSub) {
        super(inSub);
        if( inSub != null) {
            hull = inSub.getHull();
            maxDepth = inSub.getMaxDepth();
        }
        else {
            throw new IllegalArgumentException("Error construcing copy of submarine: submarine is null");
        }
    }

    // Returns a new Submarine object containing the same information as this object
    public Submarine clone() {
        return new Submarine(this);
    }
    @Override
    public double calcTravel(int dist)  {
        double travelTime;
        double leftHandSide = (double)dist / (double)getEngine().getCylinders();
        double rightHandSide = 1.0 / (10.0 + Math.abs(maxDepth));
        travelTime = leftHandSide * rightHandSide;
        return travelTime;
    }
    @Override
    public String getType() {
        return "Submarine";
    }

    // ******************************* SETTERS ********************************** //
    public void setHull(String hull) {
        if(!validateHull(hull)) {
            throw new IllegalArgumentException(hull + " is not a valid hull\n");
        }
        this.hull = hull;
    }
    public void setMaxDepth(double maxDepth) {
        if(!validateMaxDepth(maxDepth)) {
            throw new IllegalArgumentException( maxDepth + " is not a valid max depth\n");
        }
        this.maxDepth = maxDepth;
    }

    // ******************************* GETTERS ********************************** //
    public String getHull() {return hull;}
    public double getMaxDepth() {return maxDepth;}

    // **************************** OTHER METHODS ******************************* //
    public String toString() {
        return super.toString() + "\n\tHull is: " + hull + "\n\tMax depth: " + maxDepth;
    }
    public String toCSV() {
        return "S," + super.toCSV() + "," + hull + "," + maxDepth;
    }
    public boolean equals(Object inObj) {
        boolean same = false;
        if( (inObj instanceof Submarine) && (super.equals(inObj)) ) {
            Submarine inSub = (Submarine) inObj;
            if( (hull.equals(inSub.getHull())) && (maxDepth == inSub.getMaxDepth()) ) {
                same = true;
            }
        }
        return same;
    }
    // ************************** VALIDATION METHODS ***************************** //
    private boolean validateHull(String hull) {
        boolean valid = false;
        if((hull != null) && ( !hull.equals("") ) ) {
            hull = hull.toUpperCase();
            for(int ii = 0; ii < validHulls.length; ii++) {
                if(hull.equals(validHulls[ii])) {
                    valid = true;
                }
            }
        }
        return valid;
    }
    // Depth is a negative number between 0.0 and -500.0
    private boolean validateMaxDepth(double maxDepth) {
        boolean valid = false;
        if( (maxDepth <= MIN_DEPTH) && (maxDepth <= MIN_DEPTH) ) {
            valid = true;
        }
        return valid;
    }
 }