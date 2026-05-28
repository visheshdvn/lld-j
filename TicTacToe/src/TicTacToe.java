import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import enums.Symbol;
import interfaces.MoveProvider;
import models.Board;
import models.Cell;
import models.Move;
import models.Pair;
import models.Player;
import utils.CommandlineInputProviderStrategy;

public class TicTacToe {

    Board board;
    Deque<Player> players = new LinkedList<>();
    MoveProvider inputProvier = new CommandlineInputProviderStrategy();

    // board.paintBoard();
    // board.addPiece(Symbol.X);
    // board.paintBoard();

    public void initializeGame() {
        // initilize players
        Player player1 = new Player("player 1", Symbol.X);
        Player player2 = new Player("player 2", Symbol.O);
        players.add(player1);
        players.add(player2);

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
            Player currentPlayer = players.removeFirst();
            Move move = inputProvier.getMove();
            boolean isMoveSuccessful = board.addPiece(move.row, move.column, currentPlayer.getPiece());

            if(!isMoveSuccessful) {
                System.out.println("Incorrect position chosen, try again!");
                players.addFirst(currentPlayer); // Add the player back to the queue(in the front)
                continue;
            }

            // check for winner - if yes -> return the current player
            boolean isWinner = checkForWinner(move.row, move.column, currentPlayer.getPiece());
            if(isWinner) {
                return currentPlayer;
            }

            // if not add the current player to the last and continue
            players.addLast(currentPlayer);
        }
    }

    

    public boolean checkForWinner(int row, int column, Symbol pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = false;
        boolean antiDiagonalMatch = false;

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

        if(row == column) {
            // Check Diagonally
            boolean match = true;
            for (int i = 0, j = 0; i < board.size; i++, j++) {
                Cell currentCell = board.getBoard()[i][j];
                match = match && (currentCell != null &&  currentCell.getSymbol() == pieceType);
            }
            diagonalMatch = match;
        }
        

        if(row + column == board.size-1) {
            // Check Anti-Diagonally
            boolean match = true;
            for (int i = 0, j = board.size - 1; i < board.size; i++, j--) {
                Cell currentCell = board.getBoard()[i][j];
                match = match && (currentCell != null &&  currentCell.getSymbol() == pieceType);
            }
            antiDiagonalMatch = match;
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
