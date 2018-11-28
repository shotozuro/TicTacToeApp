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

    private Button button;
    private GridLayout grid;
    private TextView winner;
    private TextView message;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.board);
        winner = findViewById(R.id.winner);
        message = findViewById(R.id.message);

        board = new Board();
    }

    public void onTapCell (View v) {
        button = (Button) v;

        String tag;
        tag = (String) button.getTag();

        int row, col;
        row = Integer.valueOf(tag.substring(0,1));
        col = Integer.valueOf(tag.substring(1,2));

        Player current_player = board.mark(row, col);
        if (current_player != null) {
            button.setText(current_player.toString());
            if (current_player == board.getWinner()) {
                winner.setText(current_player.toString());
                message.setText("WIN!");
            } else if (board.isDraw()){
                message.setText("DRAW!");
            }
        }
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
