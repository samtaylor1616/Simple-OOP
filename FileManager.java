/* ****************************** *
 * Filename: FileManager.java     *
 * Author: Samantha Taylor        *
 * Date last modified: 06/06/2019 *
 * ****************************** */
import java.lang.Exception.*;
import java.io.*;

public class FileManager {
    public static void readFile(String filename, ShipStorage storage) {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        int lineNum = 0;
        try {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while( line != null ) {
                lineNum++;
                processLine(line, lineNum, storage);
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("Could not fnd the file named: " + filename);
        }
        catch(IOException e) {
            if( fileStrm != null ) {
                try { fileStrm.close(); } catch (IOException ex2) {}
            }
            throw new IllegalArgumentException("Error in file processing: " + e.getMessage());
        }
    }

    public static void processLine(String line, int lineNum, ShipStorage storage) {
        String[] parts;
        
        try {
            parts = line.split(",");
            if(parts.length == 7) {
                char type = getType( parts[0] );
                String serialNum = parts[1];
                int year = stringToInt( parts[2] );
                int cylinders = stringToInt( parts[3] );
                String fuel = parts[4];
                
                Engine engine = new Engine(fuel, cylinders);
                Ship ship = null;
                if( type == 'S' ) {
                    String hull = parts[5];
                    double maxDepth = stringtoDouble(parts[6]);

                    ship = new Submarine(engine, hull, serialNum, maxDepth, year);
                }
                else if( type == 'F' ) {
                    double wingSpan = stringtoDouble(parts[5]);
                    String ordanace = parts[6];

                    ship = new FighterJet(engine, ordanace, serialNum, wingSpan, year);
                }
                else {
                    throw new IllegalArgumentException("Ship type: " + type + " on line: " + 
                                                lineNum + " is invalid ");
                }
                storage.addShip(ship);
            }
            else {
                System.out.println("File formate on line: " + lineNum + " is not in the correct format");
            }
        }
        catch(IllegalArgumentException e) {
            System.out.println("Error occured in file reading on line: " + lineNum + 
                                "\n\t" + e.getMessage());
        }
    }

    public static char getType(String type) {
        type = type.toUpperCase();
        if( type.length() != 1 ) {
            throw new IllegalArgumentException("Type must be a single character");
        }
        return type.charAt(0);
    }

    public static int stringToInt(String line) {
        int num;
        try {
            num = Integer.parseInt(line);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException( line + " is not an integer");
        }
        return num;
    }

    public static double stringtoDouble(String line) {
        double num;
        try {
            num = Double.parseDouble(line);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException( line + " is not an integer");
        }
        return num;
    }

    public static void writeFile(String filename, ShipStorage storage) {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            pw.println(storage.toCSV());
            pw.close();
        }
        catch(IOException e) {
            if( fileStrm != null ) {
                try{ fileStrm.close(); } catch (IOException ex2) {}
            }
            throw new IllegalArgumentException("Error in writing to file: " + e.getMessage());
        }
    }
}