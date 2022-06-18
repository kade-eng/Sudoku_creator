package com.example.sudoku_creator.Database;

import android.database.sqlite.SQLiteOpenHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sudoku_creator.Activities.MainActivity;
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

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query = "CREATE TABLE Cells (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "rowIndex INTEGER NOT NULL, " +
                    "colIndex INTEGER NOT NULL, " +
                    "value INTEGER) ";
            db.execSQL(query);
        } catch (Exception e) {
            LOG.error("Creation error: ",e);
        }
    }

    public long insert(Cell cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ROW_COLUMN, cell.getRow());
        values.put(COL_COLUMN, cell.getCol());
        values.put(VAL_COLUMN, cell.getValue());

        long id = db.insert("Cells", null, values);
        db.close();

        return id;
    }

    public void delete(Integer uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={uid.toString()};

        db.delete("Cells","uid = ?", whereArgs);
        db.close();
    }

    public void update(Cell cell)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ROW_COLUMN, cell.getRow());
        values.put(COL_COLUMN, cell.getCol());
        values.put(VAL_COLUMN, cell.getValue());

        String[] whereArgs ={String.valueOf(cell.getUid())};

        db.update("Cells",values, "uid = ?", whereArgs );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS Cells");
            onCreate(db);
        } catch (Exception e) {
            LOG.error("Creation error: ",e);
        }
    }
}