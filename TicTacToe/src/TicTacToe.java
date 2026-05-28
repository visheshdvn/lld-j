import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import enums.Symbol;
import models.Board;
import models.Pair;
import models.Player;

public class TicTacToe {

    Board board;
    Deque<Player> palyers = new LinkedList<>();

    // board.paintBoard();
    // board.addPiece(Symbol.X);
    // board.paintBoard();

    public void initializeGame() {
        // initilize players
        Player player1 = new Player("player 1", Symbol.X);
        Player player2 = new Player("player 2", Symbol.O);
        palyers.add(player1);
        palyers.add(player2);

        // initialize board
        board = new Board();
    }

    public Player playGame() {
        Player winner = null;

        while (true) {
            // paint board
            board.paintBoard();

            // check if there are free spaces;
            List<Pair<Integer, Integer>> freeCells = board.getFreeCells();
            if (freeCells.isEmpty()) {
                return winner;
            }

            // if yes - let the player play
            Player currentPlayer = palyers.removeFirst();
            int[] pos = getInsertionPosition();
            board.addPiece(pos[0], pos[1], currentPlayer.getPiece());

            // check for winner - if yes -> return the current player
            boolean isWinner = checkForWinner(pos[0], pos[1], currentPlayer.getPiece());
            if(isWinner) {
                return currentPlayer;
            }

            // if not add the current player to the last and continue
            palyers.addLast(currentPlayer);
        }
    }

    private int[] getInsertionPosition() {
        Scanner scan = new Scanner(System.in);
        String position = scan.nextLine();
        // scan.close();

        String[] coordinate = position.split(",");
        int row = Integer.valueOf(coordinate[0].trim());
        int col = Integer.valueOf(coordinate[1].trim());

        return new int[] {row, col};
    }

    public boolean checkForWinner(int row, int column, Symbol pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // Check Row
        for (int i = 0; i < board.size; i++) {
            if (board.getBoard()[row][i] == null || board.getBoard()[row][i].getSymbol() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // Check Column
        for (int i = 0; i < board.size; i++) {
            if (board.getBoard()[i][column] == null || board.getBoard()[i][column].getSymbol() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        // Check Diagonally
        for (int i = 0, j = 0; i < board.size; i++, j++) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getSymbol() != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // Check Anti-Diagonally
        for (int i = 0, j = board.size - 1; i < board.size; i++, j--) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getSymbol() != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
