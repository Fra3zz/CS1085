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
    public static void writeLineToFile(String content, String filePath){
         try(PrintWriter writer = new PrintWriter(new File(filePath))){
            writer.println(content);
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

    /**
     * Simulates a roll of two 1-6 dice and returns a concatinated string of the resulting numbers.
     * 
     * @author Fra3zz
     * @version 1.0.0
     * @return String
     * @param 
     */
    public static String diceRoll() {
        Random rand = new Random();
        return("" + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1));
    }

    public static void main(String[] args) {

    //-----------CONSTANTS------------

    //Full save path
    String SAVEPATH = "./saves.txt";

    //DEBUG true/false with default of false
    boolean DEBUG = true;

    
    //-----------METHOD CALLS------------

        writeLineToFile(SAVEPATH, SAVEPATH);
    }
}
