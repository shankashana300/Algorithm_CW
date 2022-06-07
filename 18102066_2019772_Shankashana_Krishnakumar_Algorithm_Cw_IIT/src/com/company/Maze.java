package com.company;


//18102066_2019772_Shankashana_Krishnakumar

//-----------------------------References (URL)---------------------------------//
//https://stackoverflow.com/questions/43498294/boolean-2d-array-text-file
//https://stackoverflow.com/questions/39501893/reading-from-text-file-into-2-d-array-of-strings-in-java
//https://stackoverflow.com/questions/13097497/data-values-read-from-txt-file-into-2d-array-are-getting-flipped
//https://www.softwaretestinghelp.com/java-graph-tutorial/
//https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Maze {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String fileName = ""; //empty string at first.
        //obtain file name for puzzle
        //if-else-if-else statement is used.
        if(0 == args.length) {
            System.out.println("Please enter a file name.");
            fileName = s.nextLine(); //Read next line.

            char[][] grid = parser_Reader(fileName);
            PathFinding Pat3Way = new PathFinding(grid,fileName);
            Instant StartTime = Instant.now();
            Pat3Way.shortestPath();
            Instant EndTime = Instant.now();
            System.out.println(Duration.between(StartTime,EndTime));

        } else if(1 == args.length) {
            fileName = args[0];
        } else {
            System.out.println("You have entered invalid data.");
            System.exit(1);
        }
    }

    //Char[][] array is created to insert the file into a 2D array.
    public static char[][] parser_Reader(String Filename) throws IOException {
        try
        {
            int column = 0; //height
            String line; //container used to store the read lines by the buffer reader.
            int row = 0; //width//

            FileReader fr = new FileReader(Filename); //Filename store in a "fr" object.
            BufferedReader br = new BufferedReader(fr); //"fr" value is store inside a BufferedReader object.
            br.mark(1); //resposition by calling the reset() function.
            int Lines = br.readLine().length(); //reads the whole file.
            br.reset();

            char[][] NewArray = new char[Lines][Lines]; //A 2D array created and stores the read lines in the array.

            //while is used to traverse through the 2D array.
            while ((line = br.readLine()) != null) {
                column++;
                char[] columns = line.toCharArray();
                NewArray[row] = columns;
                row++;
            }
            System.out.println("The column amount: "+ column);
            System.out.println("The row amount is: "+row);
            System.out.println();

            //2 for-loops(1st is for rows and 2nd is for the columns) are used to traverse through 2D array.
            for (int i = 0; i<NewArray.length;i++)
            {
                for (int j=0;j<NewArray[i].length;j++)
                {
                    System.out.print(NewArray[i][j]); //Display 2D array.
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            //This loop is used to display the coordinates of the elements in the puzzle.
        for(int z = 0;z<NewArray.length;z++) {
            for (int j = 0; j < NewArray[z].length; j++) {
                //location - coordinates
                System.out.println("Path coord = (" + z + " , " + j
                        + ") Value at coord = " + NewArray[z][j]);
            }
            System.out.println();
        }
            System.out.println();

            return NewArray;
        } catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("ERROR");
        }
        return new char[0][];
    }
}

