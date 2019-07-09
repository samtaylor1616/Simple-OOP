/* ****************************** *
 * Filename: Ship.java            *
 * Author: Samantha Taylor        *
 * Date last modified: 26/05/2019 *
 * ****************************** */

 public abstract class Ship {
    // Class Constants
    public static final int MIN_YEAR = 1950;
    public static final int MAX_YEAR = 2022;

    //Classfields
    private Engine engine;
    private String serialNum;
    private int year;

// *************************** ABSTRACT METHODS ****************************** //
    public abstract double calcTravel(int dist);
    public abstract Ship clone(); 
    public abstract String getType();


// ***************************** CONSTRUCTORS ******************************** //
    // Default Constructor
    public Ship() {
        engine = new Engine();
        setSerNum("123.999");
        setYear(1997);
    }

    // Alternate Constructor
    public Ship(Engine engine, String serialNum, int year) {
        try {
            setEngine(engine);
            setSerNum(serialNum);
            setYear(year);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Error constructing ship: " 
                                                + e.getMessage());
        }
    }

    // Copy Constructor
    public Ship(Ship inShip) {
        if(inShip != null) {
            engine = inShip.getEngine();
            serialNum = inShip.getSerNum();
            year = inShip.getYear();
        }
        else {
            throw new IllegalArgumentException("Error construcing copy of ship: ship is null");
        }
    }

    // ******************************* SETTERS ********************************** //
    public void setEngine(Engine engine) {
        if(engine == null) {
            throw new IllegalArgumentException("Engine cannot be null\n");
        }
        this.engine = new Engine(engine);
    }
    public void setSerNum(String serialNum) {
        if(!validateSerNum(serialNum)) {
            throw new IllegalArgumentException(serialNum + " is not a valid serial number\n");
        }
        this.serialNum = serialNum;
    }
    public void setYear(int year) {
        if(!validateYear(year)) {
            throw new IllegalArgumentException( year + " is not a valid year\n");
        }
        this.year = year;
    }

    // ******************************* GETTERS ********************************** //
    public Engine getEngine() {return new Engine(engine);}
    public String getSerNum() {return serialNum;}
    public int getYear() {return year;}

    // **************************** OTHER METHODS ******************************* //
    public boolean equals(Object inObj) {
        boolean same = false;
        if(inObj instanceof Ship) {
            Ship inShip = (Ship) inObj;
            if( (engine.equals(inShip.getEngine()) && (year == inShip.getYear()) 
                && (serialNum.equals(inShip.getSerNum())) )) {
                same = true;
            }
        }
        return same;
    }
    public String toString() {
        return "\n\tSerial number: " + serialNum + "\n\tYear: " + year + engine.toString();
    }

    public String toCSV() {
        return  serialNum + "," + year + "," + engine.toCSV();
    }

    // ************************** VALIDATION METHODS ***************************** //
    private boolean validateSerNum(String serialNum) {
        boolean valid = false;
        if((serialNum != null) && ( !serialNum.equals("") && (serialCheck(serialNum)) ) ) {
            valid = true;
        }
        return valid;
    }

    /*  a real number in the form XXX.YYY, 
        the first part of the number must 
        be in the range 100 to 300 (inclusive), 
        the second part of the number must be 
        in the range 001 to 999 (inclusive). */
    private boolean serialCheck(String serialNum) {
        boolean valid = false;
        if(serialNum.length() == 7) {
            String[] parts = serialNum.split("\\.");
            if( (parts.length == 2) && (checkRange(100, 300, parts[0]) 
                && checkRange(1, 999, parts[1]))) {
                valid = true;
            }
        }
        return valid;
    }
    private boolean validateYear(int year) {
        boolean valid = false;
        if( (MIN_YEAR <= year) && (year <= MAX_YEAR) ) {
            valid = true;
        }
        return valid;
    }
    private boolean checkRange(int min, int max, String strValue) {
        boolean valid = false;
        try {
            int value = Integer.parseInt(strValue);
            if( (min <= value) && (value <= max) ) {
                valid = true;
            }
            else {
                System.out.println(strValue);
            }
        }
        catch(NumberFormatException e) {};
        return valid;
    }
}