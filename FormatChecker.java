import java.io.File;
import java.io.FileNotFoundException;

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

        

        return valid;
    }

        //toString method

    //driver method
public static void main(String[] args) {

    //command line args loop with checking for # of args

    //for each file in the command line, create new formatchecker object and toString() to tell user if valid
    
}
}