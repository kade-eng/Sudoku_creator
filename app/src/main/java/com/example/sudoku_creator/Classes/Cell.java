package com.example.sudoku_creator.Classes;

public class Cell {
    private int uid;

    private Integer value;
    private int row;
    private int col;

    private Integer boardID;

    public Cell(){
    }

    public Cell(int row, int col){
        //coordinates
        this.row = row;
        this.col = col;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Integer getBoardID() {
        return boardID;
    }

    public void setBoardID(Integer boardID) {
        this.boardID = boardID;
    }

}
