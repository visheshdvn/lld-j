package models;

import java.util.ArrayList;
import java.util.List;

import enums.Symbol;

public class Board {
    public static final int DEFAULT_SIZE = 3;

    private Cell[][] board;
    public int size;

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
    getter
     */
    public Cell[][] getBoard() {
        return board;
    }


    /*
    functionality methods
     */

    public boolean addPiece(int row, int col, Symbol piece) {
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
}
