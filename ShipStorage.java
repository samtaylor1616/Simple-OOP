/* ****************************** *
 * Filename: ShipStorage.java     *
 * Author: Samantha Taylor        *
 * Date last modified: 06/06/2019 *
 * ****************************** */
import java.text.DecimalFormat;
import java.util.HashSet;

public class ShipStorage {
    // Class constants
    public static final int MAX_SIZE = 30;

    // Class fields
    private Ship[] storage;
    private int shipCount;

    // Default constructor
    public ShipStorage() {
        storage = new Ship[MAX_SIZE];
        shipCount = 0;
    }

    public void addShip(Ship inShip) {
        if( (inShip != null) && (shipCount < MAX_SIZE) ) {

            // Creates a new reference to the object being passed in
            storage[shipCount] = inShip.clone(); 
            shipCount++;
        }
        else{
            String errMsg;
            if(inShip == null) { errMsg = "Ship is null"; }
            else{ errMsg = "We have reached max capactity of: " + MAX_SIZE; }
            throw new IllegalArgumentException("Cannot add ship into storage: " + errMsg);
        }
    }

    // ******************************* GETTERS ********************************** //
    public int getShipCount() {
        return shipCount;
    }
    public Ship[] getShipArr() {
        Ship[] copiedArr = new Ship[shipCount];
        for(int ii = 0; ii < shipCount; ii++) {
            copiedArr[ii] = storage[ii].clone();
        }
        return copiedArr;
    }

    // **************************** OTHER METHODS ******************************* //
    public String destinationCheck(int distance) {
        DecimalFormat f = new DecimalFormat("#0.000000");
        String fastestSerialNum = "";
        String outStr = "";
        String fastestType = "";
        double fastestTime;
        
        if(shipCount > 0) {
            fastestTime = storage[0].calcTravel(distance);
            for(int ii = 1; ii < shipCount; ii++) {
                double tempTime = storage[ii].calcTravel(distance);
                if(fastestTime > tempTime) {
                    fastestTime = tempTime;
                    fastestSerialNum = storage[ii].getSerNum();
                    fastestType = storage[ii].getType();
                }
            }
            outStr = "\n\tThe fastest ship is of type: " + fastestType + ".\n\tSerial number is: " 
                     + fastestSerialNum + ".\n\tWith " + f.format(fastestTime) + " time taken.";
        } 
        else{
            outStr = "There are no ships in storage";
        }
        return outStr;
    }

    public String findDuplicates() {
        String outStr = "";
        HashSet<String> shipSet = new HashSet<String>();

        for(Ship ship : storage) {
            if( shipSet.add(ship.toString()) == false ) {
                // Duplicate element
                outStr = outStr + "\n\nThis ship is a duplicate:" + ship.toString();
            }
        }
        for(Ship ship : storage) {
            if (shipSet.contains)
        }
        if(outStr.equals("")) {
            outStr = "There are currently no duplicates.";
        }
        return outStr;
    }

    public String toString() {
        String outStr = "";
        for(int ii = 0; ii < shipCount; ii++) {
            outStr = outStr + storage[ii].toString() + "\n";
        }
        if(outStr.equals("")) {
            outStr = "There are currently no ships in storage.";
        }
        return outStr;
    }

    public String toCSV() {
        String outStr = "";
        for(int ii = 0; ii < shipCount; ii++) {
            outStr = outStr + storage[ii].toCSV() + "\n";
        }
        return outStr;
    }

    public boolean equals(Object inObj) {
        boolean same = false;
        if( inObj instanceof ShipStorage ) {
            ShipStorage inStorage = (ShipStorage) inObj;
            if( (shipCount == inStorage.getShipCount()) && ( isArrayEqual(inStorage)) ) {
                same = true;
            }
        }
        return same;
    }
    
    private boolean isArrayEqual(ShipStorage inStorage) {
        boolean same = false;
        int count = 0;
        Ship[] inShipArr = inStorage.getShipArr();
        for( int ii = 0; ii < shipCount; ii++) {
            if( storage[ii].equals( inShipArr[ii] ) ) {
                count++;
            }
        }
        if (count == shipCount) {
            same = true;
        }
        return same;
    }
}