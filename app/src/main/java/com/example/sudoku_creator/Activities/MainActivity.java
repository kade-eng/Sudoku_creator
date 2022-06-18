package com.example.sudoku_creator.Activities;

import android.os.Bundle;

import com.example.sudoku_creator.Classes.Cell;
import com.example.sudoku_creator.R;

import androidx.appcompat.app.AppCompatActivity;

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

    private List<Cell> cellList;

    private EditText A1;
    private EditText A2;
    private EditText A3;

    //TODO move cell stuff into board class to handle all data
    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);

        cellList = new ArrayList<>();

        for (int cols = 0; cols < 2; cols++){
            for (int rows = 0; rows < 2; rows++){
                Cell cell = new Cell(rows,cols);
                cellList.add(cell);
            }
        }

        A1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        Cell theCell = cellList.stream().filter(cell -> cell.getRow() == 1 && cell.getCol() == 1)
                                .findFirst().orElse(null);

                        assert theCell != null;
                        theCell.setValue(Integer.parseInt(v.getText().toString()));

                        return true;
                }
                return false;
            }
        });
    }

    public void solvePuzzle(View view){
        LOG.info("start solving here");
    }

    public void savePuzzle(View view){
        LOG.info("save here");
    }

    public void savedPuzzles(View view){
        LOG.info("Saved puzzles");
    }
}