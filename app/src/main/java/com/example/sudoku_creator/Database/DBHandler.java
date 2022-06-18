package com.example.sudoku_creator.Database;

import android.database.sqlite.SQLiteOpenHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sudoku_creator.Activities.MainActivity;
import com.example.sudoku_creator.Classes.Board;
import com.example.sudoku_creator.Classes.Cell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBHandler extends SQLiteOpenHelper {
    private static final Logger LOG = LoggerFactory.getLogger(DBHandler.class);

    private static final String DB_NAME = "sudokuDB";
    private static final int DB_VERSION = 1;

    private static final String ROW_COLUMN = "rowIndex";
    private static final String COL_COLUMN = "colIndex";
    private static final String VAL_COLUMN = "value";
    private static final String BOARD_COLUMN = "boardID";

    private static final String CREATED_COLUMN = "createdTime";
    private static final String SOLVED_COLUMN = "solvedTime";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query2 = "CREATE TABLE Boards (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "createdTime TEXT, " +
                    "solvedTime TEXT); ";
            db.execSQL(query2);
        } catch (Exception e) {
            LOG.error("Creation error: ",e);
        }

        try {
            String query = "CREATE TABLE Cells (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "rowIndex INTEGER NOT NULL, " +
                    "colIndex INTEGER NOT NULL, " +
                    "value INTEGER, " +
                    "boardID INTEGER, " +
                    "FOREIGN KEY (boardID) references Boards (uid));";
            db.execSQL(query);
        } catch (Exception e) {
            LOG.error("Creation error: ",e);
        }
    }

    //BOARDS
    public int insert(Board board) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CREATED_COLUMN, board.getCreatedTimeStamp().toString());

        long id = db.insert("Boards", null, values);

        return (int)id;
    }

    public void deleteBoard(Integer uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={uid.toString()};

        db.delete("Boards","uid = ?", whereArgs);
    }

    public void update(Board board)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CREATED_COLUMN, board.getCreatedTimeStamp() == null ?
                "" : board.getCreatedTimeStamp().toString());
        values.put(SOLVED_COLUMN, board.getSolvedTimeStamp() == null ?
                "" : board.getSolvedTimeStamp().toString());

        String[] whereArgs ={String.valueOf(board.getUid())};

        db.update("Board",values, "uid = ?", whereArgs );
    }

    //CELLS
    public int insert(Cell cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ROW_COLUMN, cell.getRow());
        values.put(COL_COLUMN, cell.getCol());
        values.put(VAL_COLUMN, cell.getValue());
        values.put(BOARD_COLUMN, cell.getBoardID());

        long id = db.insert("Cells", null, values);

        return (int)id;
    }

    public void deleteCell(Integer uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={uid.toString()};

        db.delete("Cells","uid = ?", whereArgs);
    }

    public void update(Cell cell)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ROW_COLUMN, cell.getRow());
        values.put(COL_COLUMN, cell.getCol());
        values.put(VAL_COLUMN, cell.getValue());
        values.put(BOARD_COLUMN, cell.getBoardID());

        String[] whereArgs ={String.valueOf(cell.getUid())};

        db.update("Cells",values, "uid = ?", whereArgs );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS Cells");
            db.execSQL("DROP TABLE IF EXISTS Boards");
            onCreate(db);
        } catch (Exception e) {
            LOG.error("Creation error: ",e);
        }
    }
}