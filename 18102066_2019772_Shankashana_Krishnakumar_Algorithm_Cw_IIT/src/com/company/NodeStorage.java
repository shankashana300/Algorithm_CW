package com.company;

public class NodeStorage {
    private int rowNumber; //row number of the node.
    private int columnNumber; //column number of the node.
    private NodeStorage previous; //parent nodes stored.
    private String move;  //direction

    //Constructors
    public NodeStorage(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    //getters and setters created.
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public NodeStorage getPrevious() {
        return previous;
    }

    public void setPrevious(NodeStorage previous) {
        this.previous = previous;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
