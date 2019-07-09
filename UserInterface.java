/* ****************************** *
 * Filename: UserInterface.java   *
 * Author: Samantha Taylor        *
 * Date last modified: 06/06/2019 *
 * ****************************** */
import java.util.*;

 public class UserInterface {
     // Class fields
     private ShipStorage storage;

     // Constructor
     public UserInterface() {
         storage = new ShipStorage();
     }

     public void menu() {
         String prompt = "\nEnter a number between 1 and 7" +
                        "\n\t1. Read from file\n\t2. Manually add a ship" + 
                        "\n\t3. Find fastest ship\n\t4. View all ships" +
                        "\n\t5. Find Duplicates\n\t6. Save to a file" + 
                        "\n\t7. Exit\n";
        int userChoice;
        do {
            userChoice = intInput(1, 7, prompt);
            switch(userChoice) {
                case 1:
                    readFile();
                break;
                case 2:
                    manuallyAddShip();
                break;
                case 3:
                    // need to get distance
                    System.out.println(storage.destinationCheck(20));
                break;
                case 4:
                    System.out.println(storage.toString());
                break;
                case 5:
                    System.out.println(storage.findDuplicates());
                break;
                case 6:
                    saveFile();
                break;
                case 7: 
                    System.out.println("\nGoodbye!\n");
                break;
            }
         }while(userChoice != 7);
     }

    public void readFile() {
        Scanner sc = new Scanner(System.in);
        String filename;
        try {
            filename = getUserString("Enter a filename to read from");

            FileManager.readFile(filename, storage);
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
    public void saveFile() {
        String filename;
        try{
            filename = getUserString("Enter a filename to write to");

            FileManager.writeFile(filename, storage);
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void manuallyAddShip() {
        int userChoice;
        String shipMenu = "Select a number between 1 and 3" +
                          "\n\t1. Submarine\n\t2. FighterJet\n\t" +
                          "3. Cancel";
        userChoice = intInput(1, 3, shipMenu);
        if (userChoice == 1) {
            buildSub();
        }
        else if (userChoice == 2) {
            buildJet();
        }
        else {
            System.out.println("... Going back to the main menu ...");
        }
    }

    public String getUserString(String prompt) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        try {
            System.out.println(prompt);
            userInput = sc.nextLine();
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return userInput;
    }

    // Inclusive check
    public int intInput(int lower, int upper, String prompt) {
        Scanner sc = new Scanner(System.in);
        int num;
        String outprompt = prompt;

        do {
            try {
                System.out.print(outprompt + "\n");
                num = sc.nextInt();
            }
            catch(InputMismatchException e) {
                sc.nextLine(); // Clears the buffer
                num = lower - 1;
            }
            outprompt = "ERROR: please enter a valid value between " + lower + " and " + upper + "\n" + prompt;
        }while( (num < lower) || (num > upper) );

        return num;
    }

    // Inclusive check
    public double realInput(double lower, double upper, String prompt) {
        Scanner sc = new Scanner(System.in);
        double num;
        String outprompt = prompt;

        do {
            try {
                System.out.print(outprompt + "\n");
                num = sc.nextDouble();
            }
            catch(InputMismatchException e) {
                sc.nextLine(); // Clears the buffer
                num = lower - 1;
            }
            outprompt = "ERROR: please enter a valid value between " + lower + " and " + upper + "\n" + prompt;
        }while( (num < lower) || (num > upper) );

        return num;
    }

// TODO: Check the menu breaks if the storage is full
    public void buildSub() {
        System.out.println(" ########################### BUILDING THE SUBMARINE ########################### ");
        Submarine newSub;
        boolean done = false;

        do {
            try {
                newSub = new Submarine();

                buildShip(newSub);

                newSub.setHull( getUserString("Enter the hull:") );
                newSub.setMaxDepth( realInput(Submarine.MAX_DEPTH, Submarine.MIN_DEPTH, "Enter the max depth:") );
            
                done = true;
                storage.addShip(newSub);
            }
            catch (IllegalArgumentException e) {
                System.out.println("ERROR - when creating the submarine: " + e.getMessage() + "\nPlease try again");
            }
        } while (!done);
    }

    public void buildJet() {
        System.out.println(" ########################### BUILDING THE FIGHTERJET ########################### ");
        FighterJet newJet;
        boolean done = false;

        do {
            try {
                newJet = new FighterJet();

                buildShip(newJet);

                newJet.setOrdanace( getUserString("Enter the ordanace") );
                newJet.setWingSpan( realInput(FighterJet.MIN_WING_SPAN, FighterJet.MAX_WING_SPAN, "Enter the wing span:") );
            
                done = true;
                storage.addShip(newJet);
            }
            catch (IllegalArgumentException e) {
                System.out.println("ERROR - when creating the fighterjet: " + e.getMessage() + "\nPlease try again");
            }
        } while (!done);
    }

    public void buildShip(Ship ship) {
        Engine engine;
        boolean done = false;
        do {
            try {
                engine = new Engine();
                buildEngine(engine);

                ship.setSerNum( getUserString("Enter the serial number - format XXX.YYY eg. 121.665:") );
                ship.setYear( intInput(Ship.MIN_YEAR, Ship.MAX_YEAR, "Enter the year:") );

                done = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println("ERROR - when creating the ship: " + e.getMessage() + "\nPlease try again");
            }
        } while (!done);
    }

    public void buildEngine(Engine engine) {
        boolean done = false;
        do {
            try {
                engine.setFuel( getUserString("Enter the fuel type:") );
                engine.setCylinders( intInput(Engine.MIN_CYL, Engine.MAX_CYL, "Enter the number of cylinders:") );

                done = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println("ERROR - when creating the ship: " + e.getMessage() + "\nPlease try again");
            }
        } while (!done);
    }
 }