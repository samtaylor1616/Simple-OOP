import java.util.*;

public class FighterJetTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine engine = new Engine();
            FighterJet[] jets = new FighterJet[4];

            // Testing constructors
            jets[0] = new FighterJet();
            jets[1] = new FighterJet(engine, "Steel", "123.001", 12.36, 1993);
            jets[2] = new FighterJet(jets[1]);
            jets[3] = jets[0].clone();

            //print out created objects
            System.out.println("\nCONSTRUCTOR TESTS:");
            for(int i = 0; i < jets.length; i++)
            {
                System.out.println("\nJets[" + i + "]: " + jets[i].toString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("\tEquals jet index: [0] and [3] expected TRUE: " + jets[0].equals(jets[3]));
            System.out.println("\tEquals jet index: [0] and [1] expected FALSE: " + jets[0].equals(jets[1]));
   
            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            jets[0].setSerNum(jets[1].getSerNum());
            System.out.println("\t" + jets[0].getSerNum() + " = " + jets[1].getSerNum());

            jets[0].setYear(jets[2].getYear());
            System.out.println("\t" + jets[0].getYear() + " = " + jets[2].getYear());
            System.out.println();

            jets[0].setWingSpan(jets[2].getWingSpan());
            System.out.println("\t" + jets[0].getWingSpan() + " = " + jets[2].getWingSpan());
            System.out.println();

            jets[0].setOrdanace(jets[2].getOrdanace());
            System.out.println("\t" + jets[0].getOrdanace() + " = " + jets[2].getOrdanace());
            System.out.println();
            
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}