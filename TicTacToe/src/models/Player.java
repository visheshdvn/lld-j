package models;

import enums.Symbol;

public class Player {

    private String playerId;
    private Symbol piece;

    public Player(String playerId, Symbol piece) {
        this.playerId = playerId;
        this.piece = piece;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Symbol getPiece() {
        return piece;
    }
}