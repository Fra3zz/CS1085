/*
 
Write a java program with the following requirements:

declare an integer array with 20 values example: {1,20,15,5,1,6,9,10,1,8,7,3,2,1,4,13,1,17,18,19} 

print out the original array

sort the array from smallest to largest. If there are any repeating numbers, you must purge that

number from the sorted array making sure it only appears once in the array, so the above array should 

result in: {1,2,3,4,5,6,7,8,9,10,13,15,17,18,19,20} 

print out the newly sorted array

You must comment all of your code and must be ready to explain its workings on 
demand in order to receive any credit. I will change the source array to any 
combination of positive integers I choose, and your code must work.  
You must submit a valid java file.  Your code must only have two outputs, 
the original array and the sorted array, if the sorted array is smaller 
than the original array, you must only print out valid data and no null data.

This is worth 10 points extra credit on ANY EXAM.

 */

/*
-------------------------------------------------------------------
 DATA:

 1.0.0 - aa69ac78452d1666914be9222f6ea619ae6cccda
 Iterations until sorted: 362


 -------------------------------------------------------------------
 */


public class EC2 {

    public static void main(String[] args) {

        //Variables
        boolean DEBUG = true;
        
        int[] mainArry = {1,20,15,5,1,6,9,10,1,8,7,3,2,1,4,13,1,17,18,19} ;
        int[] finalArry;
        int i;
        int count = 1;
        int buffer;
        int iteration;


        System.out.print("Numbers: ");

        //Iterate and print original array.
        for (i=0; i<mainArry.length; i++){
            System.out.printf("%s ", mainArry[i]);
        }
        System.out.print("\n");

        //Iterate mainArry
        for (iteration = 0; iteration < mainArry.length - 1; iteration++){

            //For each iteration, organize the array one time.
            for (i = 0; i < mainArry.length - 1; i++){

                if(DEBUG){
                    count++;
                    //Print out index and number associated with index.
                    System.out.printf("DEBUG Index %s; Num: %s\n", i, mainArry[i]);
    
                    //Print count from 0 for each iteration (identifies total number of numbers in array.)
                    System.out.printf("DEBUG Count: %s\n", count);
                }
    
                //Switches the index values if next index is larger.
                if(mainArry[i] > mainArry[i + 1]){
                    buffer = mainArry[i];
                    mainArry[i] = mainArry[i+1];
                    mainArry[i+1] = buffer;
    
                    if(DEBUG){
                        System.out.printf("DEBUG %s is greater than %s\n", mainArry[i], mainArry[i+1]);
                    }
    
                    
                } else{
                    if (DEBUG) {
                        System.out.printf("DEBUG %s is not greater than %s\n", mainArry[i], mainArry[i+1]);
                    }
                }
    

            }


        }

        //DEBUG prints resulting array and array length
        if(DEBUG){
            System.out.printf("DEBUG Resulting Array: ");
            for (i = 0; i < mainArry.length; i++){
                System.out.printf("%s ", mainArry[i]);
            }
            System.out.printf("\nDEBUG Array length: %s\n", mainArry.length);
        }

        //Iterated through mainArry, replacing duplicates with -1
        for (i=0; i < mainArry.length - 1; i++){
            if(mainArry[i] == mainArry[i+1]){
                if(DEBUG){
                    System.out.printf("DEBUG %s is a duplicate number and has been replaced with -1\n", mainArry[i]);
                }
                mainArry[i] = -1;
            }
        }

        System.out.print("DEBUG Numbers after duplicate replacment: ");
            for (i=0; i<mainArry.length; i++){
                System.out.printf("%s ", mainArry[i]);
            }
    }
}