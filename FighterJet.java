/* ****************************** *
 * Filename: FighterJet.java      *
 * Author: Samantha Taylor        *
 * Date last modified: 26/05/2019 *
 * ****************************** */

 public class FighterJet extends Ship {
    //Class Constant
    public static final double MIN_WING_SPAN = 2.20;
    public static final double MAX_WING_SPAN = 25.6;

    //Classfields
    private String ordanace;
    private double wingSpan;

// ***************************** CONSTRUCTORS ******************************** //
    // Default Constructor
    public FighterJet() {
        super();
        setOrdanace("Mounted artillery");
        setWingSpan(MIN_WING_SPAN);
    }

    // Alternate Constructor
    public FighterJet(Engine engine, String ordanace, String serialNum, double wingSpan, int year) {
        super(engine, serialNum, year);
        try {
            setOrdanace(ordanace);
            setWingSpan(wingSpan);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Error constructing FighterJet: " 
                                                + e.getMessage());
        }
    }

    // Copy Constructor
    public FighterJet(FighterJet inJet) {
        super(inJet);
        if( inJet != null ) {
            ordanace = inJet.getOrdanace();
            wingSpan = inJet.getWingSpan();
        }
        else {
            throw new IllegalArgumentException("Error construcing copy of fighterjet: fighterjet is null");
        }
    }

    // Returns a new FighterJet object containing the same information as this object
    public FighterJet clone() {
        return new FighterJet(this);
    }

    @Override
    public double calcTravel(int dist) {
        double travelTime;
        travelTime = (double)dist / (wingSpan * (double) getEngine().getCylinders() * 150.0);
        return travelTime;
    }

    @Override
    public String getType() {
        return "Fighter Jet";
    }

    // ******************************* SETTERS ********************************** //
    public void setOrdanace(String ordanace) {
        if(!validateOrdance(ordanace)) {
            throw new IllegalArgumentException(ordanace + " is not a valid ordanace\n");
        }
        this.ordanace = ordanace;
    }
    public void setWingSpan(double wingSpan) {
        if(!validateWingSpan(wingSpan)) {
            throw new IllegalArgumentException( wingSpan + " is not a valid wingSpan\n");
        }
        this.wingSpan = wingSpan;
    }

    // ******************************* GETTERS ********************************** //
    public String getOrdanace() {return ordanace;}
    public double getWingSpan() {return wingSpan;}

    // **************************** OTHER METHODS ******************************* //
    public String toString() {
        return super.toString() + "\n\tOrdanace is: " + ordanace + "\n\tWing span: " + wingSpan;
    }

    public String toCSV() {
        return "S," + super.toCSV() + "," + wingSpan + "," + ordanace;
    }

    public boolean equals(Object inObj) {
        boolean same = false;
        if( (inObj instanceof FighterJet) && (super.equals(inObj)) ) {
            FighterJet inJet = (FighterJet) inObj;
            if( (ordanace.equals(inJet.getOrdanace())) && (wingSpan == inJet.getWingSpan()) ) {
                same = true;
            }
        }
        return same;
    }

    // ************************** VALIDATION METHODS ***************************** //
    private boolean validateOrdance(String ordanace) {
        boolean valid = false;
        if((ordanace != null) && ( !ordanace.equals("") ) ) {
            valid = true;
        }
        return valid;
    }
    private boolean validateWingSpan(double wingSpan) {
        boolean valid = false;
        if( (MIN_WING_SPAN <= wingSpan) && (wingSpan <= MAX_WING_SPAN) ) {
            valid = true;
        }
        return valid;
    }
 }