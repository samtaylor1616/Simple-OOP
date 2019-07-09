import java.util.*;

public class EngineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine[] engines = new Engine[4];

            // Testing constructors
            engines[0] = new Engine();
            engines[1] = new Engine("BIO", 12);
            engines[2] = new Engine(engines[1]);
            engines[3] = engines[0].clone();

            //print out created objects
            System.out.println("\nCONSTRUCTOR TESTS:");
            for(int i = 0; i < engines.length; i++)
            {
                System.out.println("\tEngine[" + i + "]: " + engines[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("\tEquals engine index: [0] and [3] expected TRUE: " + engines[0].equals(engines[3]));
            System.out.println("\tEquals engine index: [0] and [1] expected FALSE: " + engines[0].equals(engines[1]));
   
            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            engines[0].setFuel(engines[1].getFuel());
            System.out.println("\t" + engines[0].getFuel() + " = " + engines[1].getFuel());

            engines[0].setCylinders(engines[2].getCylinders());
            System.out.println("\t" + engines[0].getCylinders() + " = " + engines[2].getCylinders());
            System.out.println();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}