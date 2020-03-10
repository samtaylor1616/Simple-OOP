public class Engine {
    public static final String[] FUEL_TYPES = { "BATTERY", "DIESEL", "BIO" };
    public static final int MIN_CYL = 2;
    public static final int MAX_CYL = 20;

    private String fuel;
    private int numCylinders;

    public Engine() {
        setFuel(FUEL_TYPES[0]);
        setNumCylinders(MIN_CYL);
    }

    public Engine(String fuel, int cylinders) {
        try {
            setFuel(fuel);
            setNumCylinders(cylinders);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error constructing engine: " + e.getMessage());
        }
    }

    public Engine(Engine inEngine) {
        if (inEngine != null) {
            fuel = inEngine.getFuel();
            numCylinders = inEngine.getNumCylinders();
        } else {
            throw new IllegalArgumentException("Error construcing copy of engine: engine is null");
        }
    }

    public Engine clone() {
        return new Engine(this);
    }

    public void setFuel(String fuel) {
        if (!isValidFuel(fuel)) {
            throw new IllegalArgumentException(fuel + " is not a valid fuel\n");
        }
        this.fuel = fuel;
    }

    public void setNumCylinders(int numCylinders) {
        if (!isValidNumCylinders(numCylinders)) {
            throw new IllegalArgumentException(numCylinders + " is not a valid number of cylinders\n");
        }
        this.numCylinders = numCylinders;
    }

    public String getFuel() {
        return fuel;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public boolean equals(Object inObj) {
        if (inObj instanceof Engine) {
            Engine inEng = (Engine) inObj;
            if ((fuel.equals(inEng.getFuel()) && (numCylinders == inEng.getNumCylinders()))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "\n\tEngine fuel: " + fuel + "\n\tNumber of cylinders in Engine: " + numCylinders;
    }

    public String toCSV() {
        return numCylinders + "," + fuel;
    }

    private boolean isValidFuel(String fuel) {
        if (fuel != null) {
            fuel = fuel.toUpperCase();
            for (String valFuel : FUEL_TYPES) {
                if (valFuel.equals(fuel)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidNumCylinders(int numCylinders) {
        if ((MIN_CYL <= numCylinders) && (numCylinders <= MAX_CYL)) {
            return true;
        }
        return false;
    }
}