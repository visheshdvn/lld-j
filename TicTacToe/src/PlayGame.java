import models.Player;

public class PlayGame {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.initializeGame();
        Player winner = game.playGame();

        if(winner == null) {
            System.out.println("-----Draw-----");
        } else {
            System.out.println("The winner is : " + winner.getPlayerId());
        }
    }
}
