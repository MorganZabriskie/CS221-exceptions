import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This program checks the format of a user-provided file and tells
 * the user whether it is valid or not. A valid file has the following
 * format:
 * -First row contains two integers separated by a white space. The
 * first value is the number of rows in a grid and the second value
 * is the number of columns in the grid.
 * -The rest of the file should be a grid of double values that follows
 * the number of rows and columns given in the first row.
 * An example of a correctly formatted file is:
 * 
 * 2 3
 * 1.0 2 3
 * 1 2.0 3
 * 
 * @author Morgan Zabriskie
 */

public class FormatChecker {
    //instance variables
    private String userFile;
    private boolean isValid = false;
    
    /**
     * The FormatChecker() constructor initializes the instance variables
     * for the class. userFile is the file name provided by the user, and
     * isValid is instantiated by calling the validCheck() method
     * @param inputFile is file name given by user
     */
    public FormatChecker(String inputFile) {
        this.userFile = inputFile;
        File filename = new File(userFile);
        try {
            isValid = validCheck(filename);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    //other methods
    
    /**
     * The validCheck() method checks that the provided file follows the rules
     * for a valid file:
     * -First row has two white space separated values that define the number of
     * rows and columns, respectively.
     * -The remaining file has a grid that has the correct number of rows and
     * columns, and that all values in the grid are doubles.
     * An example of a correctly formatted file is:
     * 
     * 2 3
     * 1.0 2 3
     * 1 2.0 3
     * 
     * @param filename is the user-provided file name
     * @return a boolean value that states file is valid or invalid
     * @throws FileNotFoundException if the file can't be found
     */
    private boolean validCheck(File filename) throws FileNotFoundException {
        boolean valid = true;
        int rows = 0;
        int cols = 0;
        
        Scanner scan = new Scanner(filename);
        String firstRow = scan.nextLine();
        Scanner paramScan = new Scanner(firstRow);
        paramScan.useDelimiter("\\s+");

        //scan for first row (two numbers) and find # of rows and columns
        try {
            rows = Integer.parseInt(paramScan.next());
            if (rows < 0) {
                System.out.println("Please enter a positive value for number of rows.");
                valid = false;
            }
            cols = Integer.parseInt(paramScan.next());
            if (cols < 0) {
                System.out.println("Please enter a positive value for number of columns.");
                valid = false;
            }
            if (paramScan.hasNext()) {
                System.out.println("Too many arguments found in first row. First row should have two integers: [rows], [cols] ");
                valid = false;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            valid = false;
        } catch (NoSuchElementException e) {
            System.out.print(e.toString());
            System.out.println(": Too few arguments found in first row. First row should have two integers: [rows], [cols] ");
            valid = false;
        }

        paramScan.close();

        // If first row was valid, scan rest of file to check correct amount of rows/cols and all doubles
        if (valid) {
            Double[][] fileCheck = new Double[rows][cols];
            boolean keepGoing = true;
            try {
                for(int i = 0; i < rows; i++) {

                    if (!keepGoing) {
                        break;
                    }

                    String rowScan = scan.nextLine();
                    Scanner lineScan = new Scanner(rowScan);
                    lineScan.useDelimiter("\\s+");

                    for (int j = 0; j < cols; j++) {
                        fileCheck[i][j] = Double.parseDouble(lineScan.next());
                        if (j == (cols - 1) && lineScan.hasNextDouble()) {
                            System.out.printf("File is not in the correct format. Found too many values in row %d.\n", i);
                            valid = false;
                            keepGoing = false;
                            break;
                        }
                    }
                    lineScan.close();
                    
                    if (i == (rows - 1) && scan.hasNextDouble()) {
                        System.out.println("File is not in the correct format. File has too many rows.");
                        valid = false;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.toString());
                valid = false;
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
                valid = false;
            }
        }
        scan.close();
        return valid;
    }

    /**
     * The returnMessage() method returns if the given file is valid
     * or invalid.
     * @return String that is the return message 'VALID' or 'INVALID'
     */
    private String returnMessage() {
        String returnMessage = "";

        if (isValid) {
            returnMessage += "VALID\n";
        } else {
            returnMessage += "INVALID\n";
        }

        return returnMessage;
    }

    /**
     * The main driver method takes in file names from the user. The user
     * can enter as many file names as they want separated by spaces. The
     * program will test each file and return if it is valid or invalid.
     * 
     * Command line argument should look like:
     * $ java FormatChecker file1 [file2 ... fileN]
     * 
     * @param args is the array of file names provided by the user
     */
    public static void main(String[] args) {

        // check at least one file name was entered into command line
        if (args.length == 0) {
            System.out.println("Error: Please enter a file name. Multiple file names can be entered separated by a space.");
            System.out.println("Example: $ java FormatChecker file1 [file2 ... fileN]");
        } else {
            // for each file name given, check if valid and print results
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
                FormatChecker myChecker = new FormatChecker(args[i]);
                System.out.println(myChecker.returnMessage());
            }
        } 
    }
}