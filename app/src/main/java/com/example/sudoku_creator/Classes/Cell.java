package com.example.sudoku_creator.Classes;

public class Cell {
    private Integer value;
    private int row;
    private int col;

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
}
