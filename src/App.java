public class App {
    public static void main(String[] args) throws Exception {
        

        /* User input 5 digit number
           Ex: int num = 53954;      */

        int num = 12345;

        //First print out of original num
        System.out.println("The original number is: " + num);

        // Define number variables
        int num1, num2, num3, num4, num5;

        // Modulo minus previouse result to get 1-5 digits
        num1 = num  % 10; // = 4
        num2 =  (num % 100 - num1) / 10; // = 5
        num3 =  (num % 1000 - num2) / 100; // = 9
        num4 =  (num % 10000 - num3) / 1000; // = 3
        num5 = (num % 100000 - num4) / 10000; // = 5

        //Prints out invers of num with message
        System.out.println("The number in reverse is: " + num1 + num2 + num3 + num4 + num5);
    }
}