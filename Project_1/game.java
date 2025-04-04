import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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

    //Takes no input and returns a string of two random numbers, concatinated together.
    public static String diceRoll() {
        Random rand = new Random();
        //int arry[] = {rand.nextInt(6) + 1, rand.nextInt(6) + 1};
        return("" + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1));
    }

    // public static int numFromDice() {
    //     String numString = diceRoll();

    //     return 0;
    // }





    public static void main(String[] args) {

    //-----------CONSTANTS------------

    //Full save path
    String SAVEPATH = "./saves.txt";

    //DEBUG true/false with default of false
    boolean DEBUG = true;

    
    //-----------LOGIC------------
        
    }
}
