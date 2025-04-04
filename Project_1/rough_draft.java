import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.FileReader;

class rough_draft{

    //Generates two random numbers and returns a int array representing dice1 throw and dice2 throw.
    public static String diceRoll() {
        Random rand = new Random();
        //int arry[] = {rand.nextInt(6) + 1, rand.nextInt(6) + 1};
        return("" + rand.nextInt(6 + 1) + rand.nextInt(6 + 1));
    }

    public static void main(String[] args) {
        Random rand = new Random();

        //Define file path
        String fp = "./saves.txt";//Main output file pointer
        String ofp = "./odds.txt";//Odd file pointer
        String efp = "./evens.txt";//Equal file pointer

        //PrintWriter requires a try/catch for any errors PrintWriter and then File
        try(PrintWriter writer = new PrintWriter(new File(fp))){

        //System.out.printf("Number Generated: %s",rand.nextInt(50));
        for(int i=0; i < 10; i++){
            writer.printf("%s\n", rand.nextInt());
        }  
        } catch(IOException e) {
            System.err.printf("ERROR: %s", e.getMessage());
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            PrintWriter odds = new PrintWriter(new File(ofp));
            PrintWriter even = new PrintWriter(new File(efp));
            String line;
            int lineToNum;
            while((line = reader.readLine()) != null){
                lineToNum = Integer.parseInt(line);

            if(lineToNum % 2 == 0){
                System.out.println(line);
            } else {
                System.out.println(line);
            }

            }

        } catch(IOException e) {

        }



    }
}
