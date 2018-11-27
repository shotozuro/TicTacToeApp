package com.shotozuro.othello.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shotozuro.othello.R;
import com.shotozuro.othello.model.Board;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Board board = new Board();
    }
}
