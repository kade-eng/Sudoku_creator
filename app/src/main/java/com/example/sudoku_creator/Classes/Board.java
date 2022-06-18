package com.example.sudoku_creator.Classes;

import java.time.LocalDateTime;
import java.util.List;

public class Board {
    private int uid;

    private LocalDateTime createdTimeStamp;
    private LocalDateTime solvedTimeStamp;

    private List<Cell> cells;

    public Board(){
        this.createdTimeStamp = LocalDateTime.now();
    }

    public Board(List<Cell> cells){
        this.createdTimeStamp = LocalDateTime.now();
        this.cells.clear();
        this.cells.addAll(cells);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public LocalDateTime getSolvedTimeStamp() {
        return solvedTimeStamp;
    }

    public void setSolvedTimeStamp(LocalDateTime solvedTimeStamp) {
        this.solvedTimeStamp = solvedTimeStamp;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells.clear();
        this.cells.addAll(cells);
    }
}
