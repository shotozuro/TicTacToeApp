package com.shotozuro.othello.model;

public class Board {
    private Cell[][] cells = new Cell[3][3];
    private Player currentPlayer;
    private Player winner;
    private boolean isGameEnded;

    public Board () {
        restart();
    }

    public void restart () {
        clearBoard();
        winner = null;
        currentPlayer = Player.X;
        isGameEnded = false;
    }

    public Player mark (int row, int col) {
        Player movedPlayer = null;
        if (!isGameEnded) {
            if (!hasCellSet(row, col)) {
                movedPlayer = currentPlayer;
                cells[row][col].setPlayer(currentPlayer);

                if (isWin(currentPlayer, row, col)) {
                    winner = currentPlayer;
                    isGameEnded = true;
                } else {
                    switchPlayer();
                }
            }
        }

        return movedPlayer;
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
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    private boolean isWin (Player player, int currentRow, int currentCol) {
        return cells[currentRow][0].getPlayer() == player
                && cells[currentRow][1].getPlayer() == player
                && cells[currentRow][2].getPlayer() == player ||
                cells[0][currentCol].getPlayer() == player
                && cells[1][currentCol].getPlayer() == player
                && cells[2][currentCol].getPlayer() == player ||
                currentCol == currentRow
                && cells[0][0].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][2].getPlayer() == player ||
                currentCol + currentRow == 2
                && cells[0][2].getPlayer() == player
                && cells[1][1].getPlayer() == player
                && cells[2][0].getPlayer() == player;
    }
}
