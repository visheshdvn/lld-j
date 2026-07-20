package utils;

import java.util.Scanner;

/**
 * InputUtils
 */
public class InputUtils {

    final static Scanner scan = new Scanner(System.in);

    public static int takeIntInput(String message) {
        ask(message);
        int num = scan.nextInt();
        scan.nextLine(); // consume trailing newline left by nextInt()

        return num;
    }

    private static void ask(String message) {
        if (message != null && !message.isEmpty()) {
            System.out.print(message);
        }
    }
}