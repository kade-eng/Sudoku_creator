package com.example.sudoku_creator.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sudoku_creator.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavedActivity extends AppCompatActivity {
    private static final Logger LOG = LoggerFactory.getLogger(SavedActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_activity);

    }
}
