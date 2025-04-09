import java.util.Random;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class game {

    //Writes a single line string input to file input. 
    public static void writeLineToFile(String content, String filePath){
         try(PrintWriter writer = new PrintWriter(new File(filePath))){

        //System.out.printf("Number Generated: %s",rand.nextInt(50));
            writer.println(content);
        } catch(IOException e) {
            System.err.printf("ERROR: %s", e.getMessage());
        }
    }

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

    //Takes no input and returns a string of two random numbers, concatinated together.
    public static String diceRoll() {
        Random rand = new Random();
        //int arry[] = {rand.nextInt(6) + 1, rand.nextInt(6) + 1};
        return("" + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1));
    }

    public static void main(String[] args) {

    //-----------CONSTANTS------------

    //Full save path
    String SAVEPATH = "./saves.txt";

    //DEBUG true/false with default of false
    boolean DEBUG = true;

    
    //-----------METHOD CALLS------------

        readFile(SAVEPATH, DEBUG);
    }
}
