package models;

import enums.Symbol;

public class Cell {
    private Symbol piece;

    Cell(Symbol piece) {
        this.piece = piece;
    }

    public Symbol getSymbol() {
        return piece;
    }

    public void setSymbol(Symbol piece) {
        this.piece = piece;
    }
}
