package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PathFinding {

    private String fileName; //The File.
    private NodeStorage sourcefile; //Start,"S".
    private char[][] grid; //grid array created
    private final boolean[][] visited; //visited array created.
    private final ArrayList<NodeStorage> queue = new ArrayList<>(); //queue arraylist created.
    private boolean pathFound = false; //used as a boolean to display the steps taken to reach from "S" to "F"

    //Constructor created.
    public PathFinding(char[][] grid, String fileName) {
        this.grid = grid;
        this.fileName = fileName;
        this.visited = new boolean[grid.length][grid.length];
    }

    //method created to find the Start,"S".
    public void findsource()
    {
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Finding source
                if (grid[i][j] == 'S') {
                    sourcefile = new NodeStorage(i, j);
                    break firstLoop;
                }
            }
        }
    }

    //Validation for the coordinates to move.
    public boolean isValid(int row, int column) {
        if (row >= 0 && column >= 0
                && row < grid.length && column < grid[0].length
                && grid[row][column] != '0'
                && !visited[row][column]) {
            return true;
        }
        return false;
    }

    //Sliding of the nodes.
    public void slide(NodeStorage item, int x, int y, String direction) {
        int row = item.getRowNumber();
        int column = item.getColumnNumber();

        while(true) {
            row += y;
            column += x;

            //if the "S" has met with "0", break the loop.
            if (!isValid(row, column)) {
                break;
            }

            //if statment is used to check if 2D array coordinates are equal to 'F'.
            if (grid[row][column] == 'F') {
                NodeStorage neighbourItem = new NodeStorage(row, column);
                neighbourItem.setPrevious(item);
                neighbourItem.setMove(direction);

                queue.add(0, neighbourItem);
                visited[row][column] = true;
                break;
            }

            int nextRow  = row + y;
            int nextColumn = column + x;

            //If following conditions are met, the node moves away from the rock("0").
            if ((nextRow < 0 || nextColumn < 0) || (nextRow >= grid.length || nextColumn >= grid.length) || (grid[nextRow][nextColumn] == '0')) {
                NodeStorage neighbourItem = new NodeStorage(row, column);
                neighbourItem.setPrevious(item);
                neighbourItem.setMove(direction);

                queue.add(neighbourItem);
                visited[row][column] = true;
                break;
            }
        }
    }
    //writing shortest path
    public void writeShortestPath(NodeStorage item) throws IOException {
        ArrayList<String> pathw3y = new ArrayList<>();

        while (item.getPrevious() != null) {
            String step = "Move " + item.getMove() + " to " + "(" + (item.getColumnNumber() + 1) + ", " + (item.getRowNumber() + 1) + ")";
            pathw3y.add(step);
            item = item.getPrevious();
        }

        pathw3y.add("Start at " + "(" + (item.getColumnNumber() + 1) + ", " + (item.getRowNumber() + 1) + ")");
        int count = 1;
        Collections.reverse(pathw3y); //rows and columns are reversed.
        String output = ""; //empty string

        for (String steps : pathw3y) {
            System.out.println(count + ". " + steps);
            output += count + ". " + steps + "\n";
            count += 1;
        }
        System.out.println(count + ". Done!");
        output += count + ". Done!\n\n";
    }

    //Finding Shortest Path
    public void shortestPath() throws IOException {
        findsource();

        queue.add(sourcefile);
        visited[sourcefile.getRowNumber()][sourcefile.getColumnNumber()] = true;
        NodeStorage itemVisited = null;

       //Displaying out pathway
        while (!queue.isEmpty()) {
        itemVisited = queue.remove(0);
        int rowNumberVisited = itemVisited.getRowNumber();
        int columnNumberVisited = itemVisited.getColumnNumber();

        if (grid[rowNumberVisited][columnNumberVisited] == 'F') {
            pathFound = true;
            break;
        }

        slide(itemVisited, -1, 0, "left");
        slide(itemVisited, 1, 0, "right");
        slide(itemVisited, 0, 1, "bottom");
        slide(itemVisited, 0, -1, "up");
    }

        if (pathFound) {
        System.out.println("Path Found");
        writeShortestPath(itemVisited);

    } else {
        System.out.println("No Path Found");
    }
}
}
