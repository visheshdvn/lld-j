package utils;

import java.util.Scanner;

import interfaces.MoveProvider;
import models.Move;

public class CommandlineInputProviderStrategy implements MoveProvider {

    private final Scanner scanner;

    public CommandlineInputProviderStrategy() {
        this(new Scanner(System.in));
    }

    public CommandlineInputProviderStrategy(Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public Move getMove() {
        String position = scanner.nextLine();
        // scan.close();

        String[] coordinate = position.split(",");
        int row = Integer.valueOf(coordinate[0].trim());
        int col = Integer.valueOf(coordinate[1].trim());

        return new Move(row, col);
    }
}
