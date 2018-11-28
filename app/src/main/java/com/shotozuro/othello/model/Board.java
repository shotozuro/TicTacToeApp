package com.shotozuro.othello.model;

public class Board {
    private Cell[][] cells = new Cell[3][3];
    private Player current_player;
    private Player winner;
    private boolean isGameEnded;
    private boolean isDraw;


    public Board () {
        restart();
    }

    public void restart () {
        clearBoard();
        winner = null;
        current_player = Player.X;
        isGameEnded = false;
    }

    public Player mark (int row, int col) {
        Player moved_player = null;
        if (isGameEnded == false) {
            if (hasCellSet(row, col) == false) {
                moved_player = current_player;
                cells[row][col].setPlayer(current_player);

                if (isWin(current_player, row, col)) {
                    winner = current_player;
                    isGameEnded = true;
                } else {
                    switchPlayer();
                }
            }
        }

        return moved_player;
    }

    public boolean isDraw () {
        boolean status = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getPlayer() == null) {
                    status = false;
                }
            }
        }

        return status;
    }

    private boolean hasCellSet (int row, int col) {
        return cells[row][col].getPlayer() != null;
    }

    private void clearBoard () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Player getWinner() {
        return winner;
    }

    private void switchPlayer () {
        current_player = current_player == Player.X ? Player.O : Player.X;
    }

    private boolean isWin (Player player, int current_row, int current_col) {
        return cells[current_row][0].getPlayer() == player
                && cells[current_row][1].getPlayer() == player
                && cells[current_row][2].getPlayer() == player ||
                cells[0][current_col].getPlayer() == player
                && cells[1][current_col].getPlayer() == player
                && cells[2][current_col].getPlayer() == player ||
                current_col == current_row
                && cells[0][0].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][2].getPlayer() == player ||
                current_col + current_row == 2
                && cells[0][2].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][0].getPlayer() == player;
    }
}
