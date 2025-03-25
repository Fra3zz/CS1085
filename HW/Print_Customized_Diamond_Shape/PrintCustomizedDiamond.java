package ProgrammingForComputerScientist_1.HW.Print_Customized_Diamond_Shape;
import java.util.Scanner;


/*

    Write a program to print the diamond shape using user specified character 
    and size, where the size should be an even number greater than or equal to 6. 
    The program will keep asking the user for a valid size.

  Enter a letter: A
  Enter a size (even number no less than 6): 5
  Enter a size (even number no less than 6): 9
  Enter a size (even number no less than 6): 6
    AA
   AAAA
  AAAAAA
  AAAAAA
   AAAA
    AA


    Adds by 2 until num is reached
    Subtracts by 2 until num is 1
 */

import java.util.Scanner;

public class PrintCustomizedDiamond {
    public static void main(String[] args) {
    
    int i;
    int p;
    int r;
    Scanner scnr = new Scanner(System.in);

    System.out.print("Enter a letter: " );
    String userChar = scnr.nextLine();
    int userNum = 1;

    while (userNum < 6 || userNum % 2 != 0) {
      System.out.println("Enter a size (even number no less than 6): ");
      userNum = scnr.nextInt();
    }

    //Prints first half of the trinagle.
    i = userNum / 2;
    while(i < userNum){



      //Adds spacing.
      r = userNum; //3
      while (r > i) {
        System.out.print(" ");
        r--;
      }

      p = userNum/2;
      while (p <= i){
        System.out.print(userChar + userChar);
        p++;
      }
      
      System.out.println("");
      i++;
    }

    //Prints the second half of the triangle.
    i = userNum / 2; //3
    while(i > 0){

      //Adds spacing.
      System.out.print(" ");
      r = userNum/2;
      while (r > i) {
        System.out.print(" ");
        r--;
      }

      //Prints out character in each iteration.
      p = 1;
      while (p <= i){
        System.out.print(userChar + userChar);
        p++;
      }
      System.out.println("");
      i--;
    }







    }
}