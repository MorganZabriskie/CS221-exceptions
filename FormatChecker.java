import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FormatChecker {
    //instance variables
    private String userFile;
    private boolean isValid;
    
    //constructor(s)
    public FormatChecker(String inputFile) throws FileNotFoundException {
        this.userFile = inputFile;
        File filename = new File(userFile);

        if(filename.exists() && filename.isFile()) {
            isValid = validCheck(filename);
        }
    }

    //other methods
        //check format method
    private boolean validCheck(File filename) throws FileNotFoundException {
        boolean valid = true;
        int rows = 0;
        int cols = 0;
        
        if(filename.exists() && filename.isFile()) {
            Scanner scan = new Scanner(filename);
            String firstRow = scan.nextLine();
            Scanner paramScan = new Scanner(firstRow);
            paramScan.useDelimiter("\s");

            //scan for first row (two numbers) and find # of rows and columns
            try {
                rows = Integer.parseInt(paramScan.next());
                if (rows < 0) {
                    System.out.println("Please enter a positive value for number of rows.");
                    System.exit(1);
                }
                cols = Integer.parseInt(paramScan.next());
                if (cols < 0) {
                    System.out.println("Please enter a positive value for number of columns.");
                }
            } catch (NumberFormatException e) {
                System.out.println("File is not in correct format. File should only contain numbers.");
            }

            paramScan.close();

            //scan rest of file to check correct amount of rows/cols and all doubles
            Double[][] fileCheck = new Double[rows][cols];
            boolean keepGoing = true;
            try {
                for(int i = 0; i < rows; i++) {
                    if (!keepGoing) {
                        break;
                    }
                    String rowScan = scan.nextLine();
                    Scanner lineScan = new Scanner(rowScan);
                    lineScan.useDelimiter("\s");
                    for (int j = 0; j < cols; j++) {
                        fileCheck[i][j] = Double.parseDouble(lineScan.next());
                        if (j == (cols - 1) && lineScan.hasNext()) {
                            System.out.printf("File is not in the correct format. Found too many values in row %d.", i);
                            valid = false;
                            keepGoing = false;
                            break;
                        }
                    }
                    lineScan.close();
                    if (i == (rows - 1) && scan.hasNextLine()) {
                        System.out.println("File is not in the correct format. File has too many rows.");\
                        valid = false;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("The file is not in the correct format. Please make sure number of rows and columns are correct.");
                valid = false;
            }
        }
        return valid;
    }

        //toString method
        private String toString(File filename) {
            String returnMessage = null;

            returnMessage += filename + "\n";
            if (isValid) {
                returnMessage += "VALID\n";
            } else {
                returnMessage += "INVALID\n";
            }

            return returnMessage;
        }

    //driver method
public static void main(String[] args) throws FileNotFoundException {

    // command line args loop with checking for # of args
    
    // for each file name given, check if valid and print results
    for (int i = 0; i < args.length; i++) {
        FormatChecker myChecker = new FormatChecker(args[i]);
        myChecker.toString();
    }
    


    
}
}