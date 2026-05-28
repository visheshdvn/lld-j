package models;

import java.util.ArrayList;
import java.util.List;

import enums.Symbol;

public class Board {
    public static final int DEFAULT_SIZE = 3;

    private Cell[][] board;
    private int size;

    public Board(int size) {
        initializeBoardWithSize(size);
        this.size = size;
    }

    public Board() {
        initializeBoardWithSize(DEFAULT_SIZE);
        this.size = DEFAULT_SIZE;
    }

    private void initializeBoardWithSize(int size) {
        board = new Cell[size][size];
    }


    /*
    functionality methods
     */

    public boolean addPiece(int row, int col, Symbol piece) {
        if(row >= size || col >= size) {
            return false;
        }

        if(board[row][col] != null) {
            return false;
        }

        board[row][col] = new Cell(piece);
        return true;
    }


    public void paintBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getSymbol().name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    Pair<Integer, Integer> rowColumn = new Pair<>(i, j);
                    freeCells.add(rowColumn);
                }
            }
        }

        return freeCells;
    }

    public boolean checkForWinner(int row, int column, Symbol pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = false;
        boolean antiDiagonalMatch = false;

        // Check Row
        for (int i = 0; i < size; i++) {
            if (board[row][i] == null || board[row][i].getSymbol() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // Check Column
        for (int i = 0; i < size; i++) {
            if (board[i][column] == null || board[i][column].getSymbol() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        if(row == column) {
            // Check Diagonally
            boolean match = true;
            for (int i = 0, j = 0; i < size; i++, j++) {
                Cell currentCell = board[i][j];
                match = match && (currentCell != null &&  currentCell.getSymbol() == pieceType);
            }
            diagonalMatch = match;
        }
        

        if(row + column == size-1) {
            // Check Anti-Diagonally
            boolean match = true;
            for (int i = 0, j = size - 1; i < size; i++, j--) {
                Cell currentCell = board[i][j];
                match = match && (currentCell != null &&  currentCell.getSymbol() == pieceType);
            }
            antiDiagonalMatch = match;
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
