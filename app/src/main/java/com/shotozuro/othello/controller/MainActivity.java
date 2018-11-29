package com.shotozuro.othello.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.shotozuro.othello.R;
import com.shotozuro.othello.model.Board;
import com.shotozuro.othello.model.Player;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private TextView winner;
    private TextView message;
    private Board board;
    private TextView p1, p2;
    private String p1Name, p2Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.board);
        winner = findViewById(R.id.winner);
        message = findViewById(R.id.message);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);

        board = new Board();

        showPlayers();

    }

    private void showPlayers () {
        Bundle extras = getIntent().getExtras();
        p1Name = extras.getString("playerOneName");
        p2Name = extras.getString("playerTwoName");
        p1.setText(p1Name);
        p2.setText(p2Name);
    }

    public void onTapCell (View v) {
        Button button;
        button = (Button) v;

        String tag;
        tag = (String) button.getTag();

        int row, col;
        row = Integer.valueOf(tag.substring(0,1));
        col = Integer.valueOf(tag.substring(1,2));

        Player currentPlayer = board.mark(row, col);
        if (currentPlayer != null) {
            button.setText(currentPlayer.toString());
            if (currentPlayer == board.getWinner()) {
                showWinnerName(currentPlayer.toString());
                message.setText("WIN!");
            } else if (board.isDraw()){
                message.setText("DRAW!");
            }
        }
    }

    private void showWinnerName (String code) {
        String name = code.equals("X") ? p1Name : p2Name;
        winner.setText(name);
    }

    public void onReset (View v) {
        int childCount = grid.getChildCount();
        winner.setText("");
        message.setText("");

        board.restart();

        for (int i = 0; i < childCount; i++) {
            ((Button) grid.getChildAt(i)).setText("");
        }
    }
}
