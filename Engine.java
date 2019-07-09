/* ****************************** *
 * Filename: Engine.java          *
 * Author: Samantha Taylor        *
 * Date last modified: 26/05/2019 *
 * ****************************** */

public class Engine {
    // Class contants
    public static final String[] fuelTypes = {"BATTERY", "DIESEL", "BIO"};
    public static final int MIN_CYL = 2;
    public static final int MAX_CYL = 20;

    // Classfields
    private String fuel;
    private int cylinders;

    // ***************************** CONSTRUCTORS ******************************** //
    // Default Constructor
    public Engine() {
        setFuel(fuelTypes[0]);
        setCylinders(MIN_CYL);
    }

    // Alternate Constructor
    public Engine(String fuel, int cylinders) {
        try {
            setFuel(fuel);
            setCylinders(cylinders);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Error constructing engine: " 
                                                + e.getMessage());
        }
    }

    // Copy Constructor
    public Engine(Engine inEngine) {
        if(inEngine != null) {
            fuel = inEngine.getFuel();
            cylinders = inEngine.getCylinders();
        }
        else {
            throw new IllegalArgumentException("Error construcing copy of engine: engine is null");
        }
    }

    // Returns a new Engine object containing the same information as this object
    public Engine clone() {
        return new Engine(this);
    }


    // ******************************* SETTERS ********************************** //
    public void setFuel(String fuel) {
        if(!validateFuel(fuel) ) {
            throw new IllegalArgumentException(fuel + " is not a valid fuel\n");
        }
        this.fuel = fuel;
    }
    public void setCylinders(int cylinders) {
        if(!validateCylinders(cylinders)) {
            throw new IllegalArgumentException(cylinders + " is not a valid number of cylinders\n");
        }
        this.cylinders = cylinders;
    }

    // ******************************* GETTERS ********************************** //
    public String getFuel() {return fuel;}
    public int getCylinders() {return cylinders;}

    // **************************** OTHER METHODS ******************************* //
    public boolean equals(Object inObj) {
        boolean same = false;
        if(inObj instanceof Engine) {
            Engine inEng = (Engine) inObj;
            if( (fuel.equals(inEng.getFuel()) && (cylinders == inEng.getCylinders()) )) {
                same = true;
            }
        }
        return same;
    }
    public String toString() {
        return "\n\tEngine fuel: " + fuel + "\n\tNumber of cylinders in Engine: " + cylinders;
    }
    public String toCSV() {
        return cylinders + "," + fuel;
    }

    // ************************** VALIDATION METHODS ***************************** //
    private boolean validateFuel(String fuel) {
        boolean valid = false;
        fuel = fuel.toUpperCase();
         if( fuel != null ) {
             for( String valFuel : fuelTypes ) {
                 if(valFuel.equals(fuel)) {
                     valid = true;
                 }
             }
         }
        return valid;
    }
    private boolean validateCylinders(int cylinders) {
        boolean valid = false;
        if( (MIN_CYL <= cylinders) && (cylinders <= MAX_CYL)) {
            valid = true;
        }
        return valid;
    }
}