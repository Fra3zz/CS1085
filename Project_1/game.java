import java.util.Random;

public class game {

    public static String diceRoll() {
        Random rand = new Random();
        //int arry[] = {rand.nextInt(6) + 1, rand.nextInt(6) + 1};
        return("" + rand.nextInt(6 + 1) + rand.nextInt(6 + 1));
    }


    public static void main(String[] args) {
        
    }
}
