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


public class EC2 {

    public static void main(String[] args) {

    //EDITABLE VARIABLES
        //100 Random numbers generated from "https://gchq.github.io/CyberChef/#recipe=Pseudo-Random_Number_Generator(100,'Byte%20array')Find_/_Replace(%7B'option':'Simple%20string','string':'%5B'%7D,'%7B',true,false,true,false)Find_/_Replace(%7B'option':'Regex','string':'%5D'%7D,'%7D',true,false,true,false)"
        //Confirmed with "https://gchq.github.io/CyberChef/#recipe=Find_/_Replace(%7B'option':'Regex','string':'%7B'%7D,'',true,false,true,false)Find_/_Replace(%7B'option':'Regex','string':'%7D'%7D,'',true,false,true,false)Unique('Comma',false)Sort('Comma',false,'Numeric')&input=ezE5MSwxMjcsMTAzLDYsMTc1LDE4NywxMzQsNjAsMTM1LDcyLDE0OSwxNjgsMTMxLDcwLDEyMywxODMsMjMzLDY5LDY3LDI0OCw1NywyMCwyMTYsODAsNDAsMzcsMjIsMTg4LDEyOSw0NCwxMTIsMTMyLDE2MCwxOTgsMjUsMTgsMTEwLDE0NSwxMjQsMzYsMTEsMjI1LDE4NiwyMjUsMTM1LDE0MCwxMjcsMjExLDEyOSwyMDUsNzIsMTE1LDE3NCwxLDgsMTIwLDEwNiwxODksMjEyLDE4OSw0NiwxMSwxMDQsNCwxNTAsNjQsMTgyLDE1NSwzNiwyMCwyMzYsMjQ3LDMxLDIxMCwxNjgsMTIzLDIzMiwyMSwxOTcsMTMsMjUzLDE4Nyw1NCw4MywxNDQsNjksMTk4LDIxNywxNjgsMTUzLDE0LDE1OCwyMjIsMTE1LDE1MSwxMzksMTc1LDIzNiwyMzgsMTg0fQ&ieol=CRLF&oeol=CRLF"


    int[] mainArry = {191,127,103,6,175,187,134,60,135,72,149,168,131,70,123,183,233,69,67,248,57,20,216,80,40,37,22,188,129,44,112,132,160,198,25,18,110,145,124,36,11,225,186,225,135,140,127,211,129,205,72,115,174,1,8,120,106,189,212,189,46,11,104,4,150,64,182,155,36,20,236,247,31,210,168,123,232,21,197,13,253,187,54,83,144,69,198,217,168,153,14,158,222,115,151,139,175,236,238,184};



    //----------------


        //Variables
        int[] finalArry;
        int i;
        int count = 1;
        int buffer;
        int iteration;
        boolean DEBUG; //DEBUG true = DEBUG : false = user output DEFAULT false

        if(Boolean.parseBoolean(System.getenv("DEBUG")) == true){
            DEBUG = Boolean.parseBoolean(System.getenv("DEBUG"));
        } else{
            DEBUG = false;
        }


        System.out.print("Original Numbers: ");

        //Iterate and print original array.
        for (i=0; i<mainArry.length; i++){
            System.out.printf("%s ", mainArry[i]);
        }
        System.out.print("\n\n");

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

        //Iterated through mainArry, replacing duplicates with -1, and incrementing buffer with every number not duplicates.
        buffer = 1;
        for (i=0; i < mainArry.length - 1; i++){
            if(mainArry[i] == mainArry[i+1]){
                if(DEBUG){
                    System.out.printf("DEBUG %s is a duplicate number and has been replaced with -1\n", mainArry[i]);
                }
                mainArry[i] = -1;
            } else {
                buffer++;
            }
        }

        //DEBUG print of array replacment of duplicate numbers
        if(DEBUG){
            System.out.print("DEBUG Numbers after duplicate replacment: ");
            for (i=0; i<mainArry.length; i++){
                System.out.printf("%s ", mainArry[i]);
            }
            System.out.println("");
        }

        //Initializes new array with resulting buffer number
        finalArry = new int[buffer];
        if(DEBUG){
            System.out.printf("finalArry is of length %s\n", finalArry.length);
        }

        count = 0;

        //Assignes numbers greater than -1 to index count
        System.out.println("Resulting numbers:");
        for (i=0; i < mainArry.length; i++){
            if(mainArry[i] > -1){

                if(DEBUG){
                    System.out.printf("Adding %s to finalArry at index %s\n", mainArry[i], count);
                }

                finalArry[count] = mainArry[i];

                System.out.printf("%s ", finalArry[count]);

                count++;
            }
        }

        //Debug print of resulting finalArry
        if(DEBUG){
            System.out.printf("DEBUG Resulting Array: ");
            for (i = 0; i < finalArry.length; i++){
                System.out.printf("%s ", finalArry[i]);
            }
            System.out.printf("\nDEBUG Array length: %s\n", finalArry.length);
        }


    }
}