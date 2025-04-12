import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class game {

    /**
     * Method that writes lines to a file based upon string input and file path
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     * @throws IOException
     */
    public static void writeLineToFile(String content, String filePath, boolean DEBUG){
         try(PrintWriter writer = new PrintWriter(new File(filePath))){
            writer.println(content);
            if(DEBUG){
                System.out.printf("DEBUG: Line was writen to file '%s' with content '%s'.", filePath, content);
            }
        } catch(IOException e) {
            System.err.printf("ERROR: %s", e.getMessage());
        }
    }

    /**
     * Reads from a file input and returns void.
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     * @throws IOException
     */
    public static void readFile(String filePath, boolean DEBUG){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null){

                if(DEBUG){
                    System.out.printf("DEBUG: %s\n", line); //DEBUG only output
                }
            }
        } catch (IOException e){
            System.err.printf("\nERROR: %s",e);
        }
    }

    public static boolean searchUser(String userName, String email, boolean DEBUG) {

    return false;}
        
    /**
     * Evaluates email string for defined formatting of an appropriate email.
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     * @throws IOException
     */
    public static boolean validateEmail(String email, boolean DEBUG) {
        boolean no_invalid_chars = true;
        String[] invalidChars = {"[", "]", "\"", "\\", ":", ";", ",", "<", ">", "(", ")", "/"};
        boolean validStartChar;
        boolean validEndChar;
  
        for(int i = 0; i<invalidChars.length; i++){
            if(email.contains(invalidChars[i])){
                no_invalid_chars = false;
            }
        }
        validStartChar = Character.isLetterOrDigit(email.strip().charAt(0));
        validEndChar = Character.isLetterOrDigit(email.strip().charAt(email.length() - 1));

        if(no_invalid_chars && email.contains("@") && email.contains(".") && !email.isEmpty() && validStartChar && validEndChar){
            String split_regex = "[@]";
            String[] first_email_fragments = email.toLowerCase().trim().split(split_regex);
            if(first_email_fragments.length == 2 && !first_email_fragments[0].isBlank() && !first_email_fragments[1].isBlank()){
                if(first_email_fragments[0].startsWith(".") || first_email_fragments[0].endsWith(".") || first_email_fragments[1].startsWith(".") || first_email_fragments[0].endsWith(".")){
                    if(DEBUG){
                        System.out.printf("DEBUG: First part of email: '%s'\nDEBUG: Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                        System.out.printf("DEBUG: Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    }
                    return false;
                }
                if(DEBUG){
                    System.out.printf("DEBUG: First part of email: '%s'\nDEBUG: Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    System.out.printf("DEBUG: Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                }
                return true;
            } else{
                if(DEBUG){
                    System.out.printf("DEBUG: First part of email: '%s'\nDEBUG: Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    System.out.printf("DEBUG: Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public static void registerUser(String username, String email, boolean DEBUG){

    }

    /**
     * Simulates a roll of two 1-6 dice and returns a concatinated string of the resulting numbers.
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String diceRoll(boolean DEBUG) {
        Random rand = new Random();
        String number = "" + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
        if(DEBUG){
            System.out.printf("DEBUG: NUmber generated is %s.",number);
        }
        return(number);
    }

    public static void main(String[] args) {

    //-----------CONSTANTS------------

    //Full save path
    String SAVEPATH = "./saves.txt";

    //DEBUG true/false with default of false
    boolean DEBUG = true;

    
    //-----------METHOD CALLS------------

    }
}
