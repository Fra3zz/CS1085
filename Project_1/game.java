import java.util.Random;

import javax.management.RuntimeErrorException;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

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
         try(FileWriter writer = new FileWriter(new File(filePath), true)){
            writer.write(content + '\n');
            if(DEBUG){
                System.out.printf("DEBUG Line was writen to file '%s' with content '%s'.", filePath, content);
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
                    System.out.printf("DEBUG %s\n", line); //DEBUG only output
                }
            }
        } catch (IOException e){
            System.err.printf("\nERROR: %s",e);
        }
    }
        
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
                        System.out.printf("DEBUG First part of email: '%s'\nDEBUG Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                        System.out.printf("DEBUG Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    }
                    return false;
                }
                if(DEBUG){
                    System.out.printf("DEBUG First part of email: '%s'\nDEBUG Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    System.out.printf("DEBUG Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                }
                return true;
            } else{
                if(DEBUG){
                    System.out.printf("DEBUG First part of email: '%s'\nDEBUG Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    System.out.printf("DEBUG Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                }
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Makes SHA_256 hash of the message input, returning a byte array.
     * @author Fra3zz
     * @version 1.0.0
     * @return byte array
     * @param 
     * @throws NoSuchAlgorithmException
     */
    private static byte[] SHA_256(String data){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data.getBytes());
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("ERROR: " + e);

        }
    }

    /**
     * Returns string of base64 encoded hash of the string input.
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String SHA_256_B64(String message, boolean DEBUG){
        String data_encoded;

            data_encoded = Base64.getEncoder().encodeToString(SHA_256(message));
            if(DEBUG){
                System.out.printf("DEBUG Message base64: %s\n", data_encoded);
            }
            return data_encoded;
    }

    /**
     * Seraches for the email hash, compares the username hashes, and if matching, returnes the bankrll amount and the response code.
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String userAuth(String username, String email, String file, boolean DEBUG){
        //0 - Error; 1 - User validated; 2 - User info invalid; 3 - User not found

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){

                if(DEBUG){
                    System.out.printf("DEBUG %s\n", line); //DEBUG only output
                }

                if(line.split("[|]")[0].equals(SHA_256_B64(email, DEBUG))){

                    if(DEBUG){
                        System.out.println("DEBUG Email found");
                    }
                    if(line.split("[|]")[1].equals(SHA_256_B64(username, DEBUG))){

                        String[] user = line.split("[|]");

                        if(DEBUG){
                            System.out.printf("DEBUG User authenticated.\nDEBUG Bankroll: %s\n",user[2]);
                        }
                        return "1" + "|" + user[2]; //User found and bankroll amount and status returned.
                    }
                    else {
                        return "2|INVALID"; //Invalid credentials
                    }

                } else{
                    return "3|NOT_FOUND"; //User not found
                }
            }
        } catch (IOException e){
            System.err.printf("ERROR: %s\n",e);
            return "0|ERROR"; //Error
        }

    return "0|ERROR";}

    /**
     * Adds a user to the save file, constructing the "save" as email|username|bankroll. Returnes a boolean designating if the user was added.
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     */
    public static boolean addUser(String username, String email, String file, boolean DEBUG, int bankRoll){


        if(validateEmail(email, DEBUG)){
            //Constructs the payload as "email hash|username hash|bankroll"
            String payload = "" + SHA_256_B64(email, DEBUG) + "|" + SHA_256_B64(username, DEBUG) + "|" + bankRoll;
            writeLineToFile(payload, file, DEBUG);  
            return true;
        } else{

            if(DEBUG){
                System.err.println("ERROR: Invalid email");
            }
            return false;
        }
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
        String number = "" + (rand.nextInt(6) + 1) + "|" + (rand.nextInt(6) + 1);
        if(DEBUG){
            System.out.printf("DEBUG Number generated is %s.",number);
        }
        return(number);
    }

    /**
     * Checks if a user is registerd by comparing email hashes. Quick method for checking is a user is already registerd. 
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     */
    public static boolean isUserRegisterd(String email, String file, boolean DEBUG){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){

                if(DEBUG){
                    System.out.printf("DEBUG %s\n", line); //DEBUG only output
                }

                if(line.split("[|]")[0].equals(SHA_256_B64(email, DEBUG))){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e){
            System.err.printf("\nERROR: %s",e);
        }

    
    return false;}
    
    /**
     * Converts the diceRoll result to ints and returns an int sum.
     * @author Fra3zz
     * @version 1.0.0
     * @return int
     * @param 
     */
    public static int scoreDice(String diceRoll, boolean DEBUG) {

         String[] roll = diceRoll.split("[|]");
        return Integer.parseInt(roll[0]) + Integer.parseInt(roll[1]);
}
    
    
    
    /*
        The Come-out Roll aka “the first roll” of the two six-sided dice:

        If the sum of the numbers on the dice is either 7 or 11; the player wins and 
            the game is over.

        If the sum of the numbers on the dice is either 2, 3 or 12; 
            the player loses, and the game is over. (aka crap out) 

        If the sum of the numbers on the dice is any other number, that number 
            becomes the point, and the game continues 

        Follow-up rolls, aka “rolling the point”: 

        If the initial roll results in a point, the player will continue to roll the dice and 
            every time they roll the same point.

        If the player rolls any other number that is not “the point” or “7”, the game 
            continues to the next roll

        If the player rolls a “7”, the game is over. (aka 7 out)
         */
    
    
    public static void main(String[] args) {

    //-----------CONSTANTS------------

    //Full save path
    String SAVEPATH = "./saves.txt";

    //DEBUG true/false with default of false
    boolean DEBUG = true;

    
    //-----------METHOD CALLS------------
    }
}
