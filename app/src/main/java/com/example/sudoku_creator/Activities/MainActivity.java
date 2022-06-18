package com.example.sudoku_creator.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.sudoku_creator.Classes.Board;
import com.example.sudoku_creator.Classes.Cell;
import com.example.sudoku_creator.Database.DBHandler;
import com.example.sudoku_creator.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int idArray[][];

    private DBHandler dbHandler;

    private Board currentBoard;

    //TODO move cell stuff into board class to handle all data
    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        createGrid();
    }

    @Override
    protected void onDestroy() {
        dbHandler.close();
        super.onDestroy();
    }

    public void createGrid(){
        ConstraintLayout layout = findViewById(R.id.layout);

        int color1 = getResources().getColor(R.color.gridCol1);
        int color2 = getResources().getColor(R.color.gridCol2);

        EditText editText;
        ConstraintLayout.LayoutParams lp;
        int id;
        idArray = new int[9][9];

        ConstraintSet cs = new ConstraintSet();
        for (int iRow = 0; iRow < 9; iRow++) {
            for (int iCol = 0; iCol < 9; iCol++) {
                editText = new EditText(this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                lp = new ConstraintLayout.LayoutParams(ConstraintSet.MATCH_CONSTRAINT,
                        ConstraintSet.MATCH_CONSTRAINT);
                id = View.generateViewId();
                idArray[iRow][iCol] = id;
                editText.setId(id);

                editText.setGravity(Gravity.CENTER);
                editText.setBackgroundColor(((iRow + iCol) % 2 == 0) ? color1 : color2);
                layout.addView(editText, lp);
            }
        }

        cs.clone(layout);
        cs.setDimensionRatio(R.id.gridFrame, 9 + ":" + 9);
        for (int iRow = 0; iRow < 9; iRow++) {
            for (int iCol = 0; iCol < 9; iCol++) {
                id = idArray[iRow][iCol];
                cs.setDimensionRatio(id, "1:1");
                if (iRow == 0) {
                    cs.connect(id, ConstraintSet.TOP, R.id.gridFrame, ConstraintSet.TOP);
                } else {
                    cs.connect(id, ConstraintSet.TOP, idArray[iRow - 1][0], ConstraintSet.BOTTOM);
                }
            }

            cs.createHorizontalChain(R.id.gridFrame, ConstraintSet.LEFT,
                    R.id.gridFrame, ConstraintSet.RIGHT,
                    idArray[iRow], null, ConstraintSet.CHAIN_PACKED);
        }

        cs.applyTo(layout);
    }

    public void solvePuzzle(View view){
        LOG.info("start solving here");
    }

    public void savePuzzle(View view){
        LOG.info("save here");
        Board board = new Board();
        int boardID = dbHandler.insert(board);
        board.setUid(boardID);
        List<Cell> cells = new ArrayList<>();
        for (int iRow = 0; iRow < 9; iRow++) {
            for (int iCol = 0; iCol < 9; iCol++) {
                int id = idArray[iRow][iCol];
                EditText editText = findViewById(id);
                Cell cell = new Cell();
                cell.setValue(editText.getText().toString().isEmpty() ? null :
                        Integer.parseInt(editText.getText().toString()));
                cell.setBoardID(boardID);
                int cellID = dbHandler.insert(cell);
                cell.setUid(cellID);
                cells.add(cell);
            }
        }
        board.setCells(cells);
        currentBoard = board;
        LOG.info("Saved Board");
    }

    public void savedPuzzles(View view){
        LOG.info("Saved puzzles");
        Intent intent = new Intent(this, SavedActivity.class);
        startActivity(intent);
    }
}