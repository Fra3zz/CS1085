public class App {
    public static void main(String[] args) throws Exception {
        

        //IDEAS
        //Take teh last number, add it to the string

        //Truncate the data by mutiplying the number by a decmal and then making it an int.  Decimal to number.


        /* User input 5 digit number
           Ex: int num = 53954;      */

        int num = 59375;

        //First print out of original num
        System.out.println("The original number is: " + num);

        // Define number variables
        int num1, num2, num3, num4, num5;
        double numDouble;

        // Number isolation from num.
        num1 = num  % 10; // = 4

        //Multiples num by 1e-1 to 1e-4 to make num into a double. Double is truncated and modulo 10 to get isolated nuumber.
        numDouble = num * 0.1;
        num2 = (int)numDouble % 10;

        numDouble = num * 0.01;
        num3 = (int)numDouble % 10;

        numDouble = num * 0.001;
        num4 = (int)numDouble % 10;

        numDouble = num * 0.0001;
        num5 = (int)numDouble % 10;


        //Prints out invers of num with message
        System.out.println("The number in reverse is: " + num1 + num2 + num3 + num4 + num5);
    }
}