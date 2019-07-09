// Test Harness before class became abstract
import java.util.*;

public class ShipTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine engine = new Engine();
            Ship[] ships = new Ship[4];

            // Testing constructors
            ships[0] = new Ship();
            ships[1] = new Ship(engine, "123.001", 1993);
            ships[2] = new Ship(ships[1]);
            ships[3] = ships[0].clone();

            //print out created objects
            System.out.println("\nCONSTRUCTOR TESTS:");
            for(int i = 0; i < ships.length; i++)
            {
                System.out.println("\nShips[" + i + "]: " + ships[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("\tEquals ship index: [0] and [3] expected TRUE: " + ships[0].equals(ships[3]));
            System.out.println("\tEquals ship index: [0] and [1] expected FALSE: " + ships[0].equals(ships[1]));
   
            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            ships[0].setSerNum(ships[1].getSerNum());
            System.out.println("\t" + ships[0].getSerNum() + " = " + ships[1].getSerNum());

            ships[0].setYear(ships[2].getYear());
            System.out.println("\t" + ships[0].getYear() + " = " + ships[2].getYear());
            System.out.println();
            
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}