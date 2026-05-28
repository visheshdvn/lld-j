package utils;

import java.util.Scanner;

import interfaces.MoveProvider;
import models.Move;

public class CommandlineInputProviderStrategy implements MoveProvider {
    
    @Override
    public Move getMove() {
        Scanner scan = new Scanner(System.in);
        String position = scan.nextLine();
        // scan.close();

        String[] coordinate = position.split(",");
        int row = Integer.valueOf(coordinate[0].trim());
        int col = Integer.valueOf(coordinate[1].trim());

        return new Move(row, col);
    }
}
