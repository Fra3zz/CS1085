import java.util.Random;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
         try(FileWriter writer = new FileWriter(new File(filePath), true)){ // Try..Catch fro FileWriter
            writer.write(content + '\n'); //Write content to file next line.
            if(DEBUG){
                System.out.printf("DEBUG Line was writen to file '%s' with content '%s'.", filePath, content); //DEBUG
            }
        } catch(IOException e) {
            System.err.printf("ERROR: %s", e.getMessage()); //Exception
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
        String[] invalidChars = {"[", "]", "\"", "\\", ":", ";", ",", "<", ">", "(", ")", "/", " "}; //Array of characters that are not permitted in email.
        boolean validStartChar;
        boolean validEndChar;

        if(email.isBlank() || email.isEmpty() || email.strip().length() < email.length()){ //Checks if the email input is empty, blank, or has any spaces that should not be in the email.
            return false;
        }
  
        for(int i = 0; i<invalidChars.length; i++){
            if(email.contains(invalidChars[i])){ //Iterates for invalid characters in the email.
                no_invalid_chars = false;
            }
        }
        validStartChar = Character.isLetterOrDigit(email.strip().charAt(0)); //Checks if the first character in the email is only a letter or a digit
        validEndChar = Character.isLetterOrDigit(email.strip().charAt(email.length() - 1)); //Checks if the last character in the email is only a letter or a digit

        if(no_invalid_chars && email.contains("@") && email.contains(".") && !email.isEmpty() && validStartChar && validEndChar){ //Varifies if all of the email validation criteria is valid.
            String split_regex = "[@]"; //Regex for spliting at "@" symbol.
            String[] first_email_fragments = email.toLowerCase().trim().split(split_regex);
            if(first_email_fragments.length == 2 && !first_email_fragments[0].isBlank() && !first_email_fragments[1].isBlank()){
                if(first_email_fragments[0].startsWith(".") || first_email_fragments[0].endsWith(".") || first_email_fragments[1].startsWith(".") || first_email_fragments[0].endsWith(".")){ //Checks if either the first of last portion of the email contains ".", returning the email as invalid.
                    if(DEBUG){ //DEBUG
                        System.out.printf("DEBUG First part of email: '%s'\nDEBUG Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                        System.out.printf("DEBUG Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    }
                    return false;
                }
                if(DEBUG){ //DEBUG
                    System.out.printf("DEBUG First part of email: '%s'\nDEBUG Second part of email: '%s'\n", first_email_fragments[0], first_email_fragments[1]);
                    System.out.printf("DEBUG Full email: '%s@%s'\n", first_email_fragments[0], first_email_fragments[1]);
                }
                return true;
            } else{
                if(DEBUG){ //DEBUG
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
     * Validates username with username and DEBUG input utilizing regex compareison
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     * @throws IOException
     */
    public static boolean validateUsername(String username, boolean DEBUG) {
        return username.matches("[A-Za-z]+"); //Checks if username has one or more characters in A-Z and a-z.
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
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); //Mkaes MessageDigest object, setting SHA-256 as the provider.
            return digest.digest(data.getBytes()); //Makes byte SHA-256 hash 
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("ERROR: " + e); //If any errors, returns an error.

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

            data_encoded = Base64.getEncoder().encodeToString(SHA_256(message)); //Gets SHA-256 bytes and base64 encodes them, asigning them to data_encoded.
            if(DEBUG){
                System.out.printf("DEBUG Message base64: %s\n", data_encoded);
            }
            return data_encoded;
    }

    /**
     * Seraches for the email hash, compares the username hashes, and if matching, returnes the bankroll amount and the response code as "code | bankroll or error status". 
     *  Returns 0 = ERROR, 1 = User Validated, 2 = User info invalid, 3 = User not found, 4 - bank empty.
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String userAuth(String username, String email, String file, boolean DEBUG){
        //0 - Error; 1 - User validated; 2 - User info invalid; 3 - User not found; 4 - Users bank empty

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

                        if(line.split("[|]")[2].equals("0")){
                            return "4|EMPTY_BANK";
                        }

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
            String payload = "" + SHA_256_B64(email, DEBUG) + "|" + SHA_256_B64(username, DEBUG) + "|" + bankRoll; //Makes string to add to save file.
            writeLineToFile(payload, file, DEBUG); //Write line to file.
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
            System.out.printf("DEBUG Number generated is %s.\n",number);
        }
        return(number);
    }

    /**
     * Checks if a user is registerd by comparing email hashes. Quick method for checking if a user is already registerd. 
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
     * Converts the diceRoll string result to ints and returns an int sum.
     * @author Fra3zz
     * @version 1.0.0
     * @return int
     * @param 
     */
    public static int scoreDice(boolean DEBUG) {
        String diceRoll = diceRoll(DEBUG); //Rolls dice
        String[] roll = diceRoll.split("[|]");
        int total = Integer.parseInt(roll[0]) + Integer.parseInt(roll[1]);
        System.out.printf("Roll:  %s-%s\nTotal: %s.\n", roll[0], roll[1], total);
        return total;
}
    

    /**
     * Edites the bankroll of designated users account identifying the account on users email hash, 
     * replacing it with a new(updated) string, writes it, if not users account, original line is writen to file. Keeps user info in
     * the same order. Takes in file string, email string, bankroll string, and boolean DEBUG
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     */
    public static void editBankRoll(String file, String email, int newbBankRollAmount, boolean DEBUG) {
        List<String> lines = new ArrayList<>(); //Makes a new List of strings type.
        boolean lineReplaced = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (DEBUG) {
                    System.out.printf("DEBUG %s\n", line); // DEBUG only output
                }

                if (line.split("[|]")[0].equals(SHA_256_B64(email, DEBUG))) { //Checks if the users email is the same as the user input email SHA256 base 64 encoded
                    String[] user = line.split("[|]");
                    String payload = user[0] + "|" + user[1] + "|" + newbBankRollAmount; //Creates new formated string.
                    lines.add(payload); //Replaces line with payload new paylaod
                    lineReplaced = true;

                    if (DEBUG) {
                        System.out.printf("DEBUG Line will be replaced with '%s'.\n", payload);
                    }
                } else { //If the email SHA256 dose not equial the user email, replace with original string
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.printf("\nERROR: %s", e);
            return;
        }

        if (lineReplaced) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) { 
                    writer.write(l); //Writes replaced/oringinal line to file.
                    writer.newLine();//Adds new line to file
                }
                if (DEBUG) {
                    System.out.printf("DEBUG File '%s' has been updated.\n", file);
                }
            } catch (IOException e) {
                System.err.printf("ERROR: %s", e.getMessage());
            }
        } else if (DEBUG) {
            System.out.printf("DEBUG No matching line found for email '%s'.\n", email);
        }
    }


    /**
     * Evaluates the "first roll" from the dice roll sum input. Reduces and increases bankroll based upon win/lose criteria. Can return "GOOD_END",
     * "BAD_END", and "???"".
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String evaluateDice(int diceScore, int bet, boolean DEBUG){
        if(diceScore == 7 || diceScore == 11){
            if (DEBUG) {
                System.out.printf("DEBUG User won first roll. Bet %s added to bankroll\n", bet);
            }
            return "GOOD_END"; //Game ends with bet added to bankroll.
        } else if(diceScore == 2 || diceScore == 3 || diceScore == 12){
            if (DEBUG) {
                System.out.printf("DEBUG User lost first roll, %s deducted from bankroll\n", bet);
            }
            return "BAD_END"; //Game ends with bet lost.
        } else {
            if (DEBUG) {
                System.out.println("DEBUG User is on point");
            }
            return "???"; //Player keeps pushing their luck.
        }
    }

    /**
     * Method for the registration interface based upon users decision in manu. Returns true if the user was successfully registerd.
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     */
    public static boolean regUserIf(Scanner scnr, boolean DEBUG, String file, int startinggBankRoll) {
        String username;
        String email;

        System.out.println("Please input your desired username (A-Z, a-Z, no spaces): ");
        username = scnr.nextLine().toString(); // Username input with default to string.

        while(!validateUsername(username, DEBUG)){
            System.out.println("Invalid username. Please input a valid username(A-Z, a-Z, no spaces): ");
            username = scnr.nextLine().toString(); //Username invalid, request new username.
        }

        System.out.println("Please input your email (No special characters): ");
         email = scnr.nextLine().toString().toLowerCase(); //Email input

        while(!validateEmail(email, DEBUG)){
            System.out.println("Invalid email. Please input a valid email: ");
            email = scnr.nextLine().toString().toLowerCase(); //If email invalid, new email input
        }

        if(isUserRegisterd(email, file, DEBUG)){ //Check if the users's email is already registerd.
            System.out.println("User is already registerd, please loggin.");
            username = null;
            email = null;
            return false;
        } else {
            addUser(username, email, file, DEBUG, startinggBankRoll);
            return true;
        }

    }

    /**
     * Main logic for rolling point. Takes in point socre, bet amount, scanner object, file path, email, bank amount, and debug, returning the user to the table menu if failed or player quits.
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     */
    public static int rollPoint(int point, int bet, Scanner scanner, String file, String email, int bank, boolean DEBUG){
        boolean fail = false;

        while(!fail){
            System.out.println("Roll the dice? (ENTER): \n");
            scanner.nextLine().toString();

            int roll = scoreDice(DEBUG);

            if(roll == point){
                editBankRoll(file, email, bank + bet, DEBUG);
                bank += bet;
                System.out.printf("You hit point:)\n  %s added to your bank.\nBank: %s \n", bet, bank);
            } else if(roll == 7){
                editBankRoll(file, email, bank - bet, DEBUG);
                bank = bank - bet;
                fail = true;
                System.out.printf("You hit 7 :(\n %s removed from your bank.\nBank: %s\n", bet, bank);
                return bank;
            } else {
                System.out.printf("You did not hit %s(point) or 7. Keep em' rolling!\n", point);
            }
        }
        return 0;
    }

    /**
     * Performs validation of user input with conversion in a try/catch. If not able to convert to an int, throws error and returns false.
     * @author Fra3zz
     * @version 1.0.0
     * @return boolean
     * @param 
     */
    public static boolean validateInt(String input, boolean DEBUG) {
        try{
            Integer.valueOf(input); //Tries to convert the string value to an int. If unable to (due to invalid user input), 
            //throws error indicating invalid conversion and invalid intiger.
            if(Integer.valueOf(input) > 0){ //Checks if value is greater than 0 (positive ints only).
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Log-in interface for authenticating username and email returning a string with the authorized users bankroll and email or MENU code to return to the menu.
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String logginIf(Scanner scanner, String file, boolean DEBUG){ //Dose not check for valid username and email for security.
        System.out.println("Please input your registerd username: ");
        String username = scanner.nextLine().toString();
        System.out.println("Please input your esmail: ");
        String email = scanner.nextLine().toString().toLowerCase();
        String authorization = userAuth(username, email, file, DEBUG);
        String info;

        while (!authorization.split("[|]")[0].equals("1") && !authorization.split("[|]")[0].equals("4")) {

            //Gets the username again
            System.out.println("Invalid username or email. \nPlease enter your username or return to the main menu(0): ");
            username = scanner.nextLine().toString();

            if(username.equals("0")){
                return "MENU";
            }

            //Gets the email again.
            System.out.println("Please input your email or return to the main menu(0): ");
            email = scanner.nextLine().toString().toLowerCase();

            if(email.equals("0")){
                return "MENU";
            }

            authorization = userAuth(username, email, file, DEBUG);
        }

        if(authorization.split("[|]")[0].equals("4")){
            return "4|EMPTY_BANK";
        }
        info = authorization.split("[|]")[1] + "|" + email;

        System.out.printf("You have been authorized %s\n", username);
        System.out.printf("Your current bankroll is $%s. Taking you to the table...\n", info.split("[|]")[0]);
        return info;
    }

    /**
     * Main game logic that takes bank roll amount, scanner object, file path, email, and debug settings. Performs game logic.
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     */
    public static void play_game(String bankRoll, Scanner scanner, String file, String email, boolean DEBUG){
        String choice = "";
        int bank = Integer.parseInt(bankRoll);
        int bet = 0;
        int point = 0;
        String userInput = "";

        while(!choice.equals("0")){
            System.out.println("Welcome to the table! You can choose from the menu below: ");
            System.out.printf("1 - Play game\n0 - Back to main menu\n\nCurrent Bankroll: $%s\n", bank);

            choice = scanner.nextLine().toString();

            if(choice.equals("1")){
                boolean valid = false;
                System.out.println("How much do you want to bet?: ");
                while(!valid){
                    userInput = scanner.nextLine().toString();

                    if(validateInt(userInput, DEBUG)){
                        bet = Integer.parseInt(userInput);
                    }
                    if(bet <= bank && bet > 0){
                        valid = true;
                        
                    } else {
                        System.out.printf("Oops! You placed a bigger bet than your bankroll or you didnt input an intiger.\nYour current bankroll is $%s.\nPlease input your bet: ", bank);
                    }
                }

                System.out.println("Roll the dice? (ENTER)");
                scanner.nextLine().toString();

                //First roll game results as int.
                int rollInt = scoreDice(DEBUG);

                if(evaluateDice(rollInt, bet, DEBUG).equals("GOOD_END")){
                    editBankRoll(file, email, bank + bet, DEBUG); //Adds bet amount to users bank.
                    System.out.printf("YOU WIN\n%s added to bank for a total of %s\n", bet, bank + bet);
                    bet = 0;
                } else if (evaluateDice(rollInt, bet, DEBUG).equals("BAD_END")){
                    editBankRoll(file, email, bank - bet, DEBUG); //Removes bet amount from bank.
                    System.out.printf("YOU LOST\n%s removed from your bank for a total of %s\n", bet, bank - bet);
                    bet = 0;
                } else {
                    point = rollInt;
                    rollInt = 0;

                    System.out.printf("You are rolling point with a point of %s\n", point);
                    bank = rollPoint(point, bet, scanner, file, email, bank, DEBUG);
                }
            }
        }
    }

    /**
     * Check is the save file exists, if not it makes the file. Accepts file string and DEBUG boolean.
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     */
    public static void checkForSaveFile(String file, boolean DEBUG) {
        File fileCheck = new File(file);
        if(!fileCheck.exists()){ //Checks if file exists.
            try(FileWriter writer = new FileWriter(fileCheck)){
                if(DEBUG){
                    System.out.println("Making file."); //DEBUG
                }
                writer.write("");
            } catch (Exception e){
                System.err.println("ERROR: " + e);
            }
        }
    }


    /**
     * As criteria was to not have any logic in the main main method, majority of the starting logic is in this method. This method initializes the starting menu.
     * @author Fra3zz
     * @version 1.0.0
     * @return void
     * @param 
     */
    public static void start(Scanner scnr, String name, boolean DEBUG, String file, int startinggBankRoll) {

        boolean authorized = false;
        String choice = "";

        checkForSaveFile(file, DEBUG);

        while (!authorized && !choice.equals("3")){
            System.out.printf("Welcome to %ss Dice Game: Main Menu\n\n", name); //MOTD
            System.out.println("Please pay attention as our menu options have changed. Type in your desired shoice (1, 2, or 3)");

            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Quit\n");

            choice  = scnr.nextLine().toString();

            while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
                System.out.println("Invalid choice. Please pick fom the menu.");
                System.out.println("1 - Login");
                System.out.println("2 - Register");
                System.out.println("3 - Quit\n");

                choice  = scnr.nextLine().toString();
            }

                //Log-in = 1
                if(choice.equals("1")){

                    String authorizedAndBankroll = logginIf(scnr, file, DEBUG);

                    if(!authorizedAndBankroll.equals("MENU") && !authorizedAndBankroll.equals("4|EMPTY_BANK")){
                        play_game(authorizedAndBankroll.split("[|]")[0], scnr, file, authorizedAndBankroll.split("[|]")[1], DEBUG);
                    } else if(authorizedAndBankroll.equals("4|EMPTY_BANK")){
                        System.out.println("Sorry but you do not have any money!");
                    }
                }

                // Register = 2
                if(choice.equals("2")){
                    if(regUserIf(scnr, DEBUG, file, startinggBankRoll)){
                        System.out.println("Thank you for registering. Please log-in(1).");
                    }
                }

                // Exit/Quite = 3
                if(choice.equals("3")){

                    // Good bye
                    System.out.println("Have a greate day and don't forget to cash out!!");
                    return;
                }
            } 
        }

        public static void main(String[] args) {

            //-----------CONSTANTS------------
        
            //Settings
            String SAVEPATH = "./saves.txt"; //File path
            boolean DEBUG = false; //DEBUG
            int STARTBR = 100; //Default bankroll allocated to user on registration
            String NAME = "Fra3zz"; //Authors name
    
            //-----------Utils------------
            Scanner scnr = new Scanner(System.in);
        
            //-----------METHOD CALLS------------
            start(scnr, NAME, DEBUG, SAVEPATH, STARTBR); //Starts the application
        
            }
}
