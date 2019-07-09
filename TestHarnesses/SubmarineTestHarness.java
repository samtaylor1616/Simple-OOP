import java.util.*;

public class SubmarineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine engine = new Engine();
            Submarine[] subs = new Submarine[4];

            // Testing constructors
            subs[0] = new Submarine();
            subs[1] = new Submarine(engine, "Steel", "123.001", -230, 1993);
            subs[2] = new Submarine(subs[1]);
            subs[3] = subs[0].clone();

            //print out created objects
            System.out.println("\nCONSTRUCTOR TESTS:");
            for(int i = 0; i < subs.length; i++)
            {
                System.out.println("\nSubs[" + i + "]: " + subs[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("\tEquals sub index: [0] and [3] expected TRUE: " + subs[0].equals(subs[3]));
            System.out.println("\tEquals sub index: [0] and [1] expected FALSE: " + subs[0].equals(subs[1]));
   
            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            subs[0].setSerNum(subs[1].getSerNum());
            System.out.println("\t" + subs[0].getSerNum() + " = " + subs[1].getSerNum());

            subs[0].setYear(subs[2].getYear());
            System.out.println("\t" + subs[0].getYear() + " = " + subs[2].getYear());
            System.out.println();

            subs[0].setHull(subs[2].getHull());
            System.out.println("\t" + subs[0].getHull() + " = " + subs[2].getHull());
            System.out.println();

            subs[0].setMaxDepth(subs[2].getMaxDepth());
            System.out.println("\t" + subs[0].getMaxDepth() + " = " + subs[2].getMaxDepth());
            System.out.println();
            
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}