import java.io.File;
import java.io.FileNotFoundException;
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
        boolean valid = false;
        int rows = 0;
        int cols = 0;
        
        if(filename.exists() && filename.isFile()) {
            Scanner scan = new Scanner(filename);
            String firstRow = scan.nextLine();
            Scanner paramScan = new Scanner(firstRow);
            paramScan.useDelimiter("\s");

            //scan for first row (two numbers) and find # of rows and columns
            rows = Integer.parseInt(paramScan.next());
            if (rows < 0) {
                System.out.println("Please enter a positive value for number of rows.");
                System.exit(1);
            }
            cols = Integer.parseInt(paramScan.next());
            if (cols < 0) {
                System.out.println("Please enter a positive value for number of columns.");
            }

            paramScan.close();

            //scan rest of file to check correct amount of rows/cols and all doubles
            Double[][] fileCheck = new Double[rows][cols];
            for(int i = 0; i < fileCheck.length; i++) {
                String rowScan = scan.nextLine();
                Scanner lineScan = new Scanner(rowScan);
                lineScan.useDelimiter("\s");
                for (int j = 0; j < fileCheck[0].length; j++) {
                    fileCheck[i][j] = Double.parseDouble(lineScan.next());
                }
                lineScan.close();
            }
        }
        return valid;
    }

        //toString method

    //driver method
public static void main(String[] args) {

    //command line args loop with checking for # of args

    //for each file in the command line, create new formatchecker object and toString() to tell user if valid
    
}
}