package ui;

import java.util.Scanner;
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    public void horizontalLines() {
        System.out.println("__________________________________________________________________");
    }

    public void lineSandwich(String message) {
        horizontalLines();
        display(message);
        horizontalLines();
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void openingMessage() {
        lineSandwich(" Hello there. I'm Whelmed.\n What do you want?");
    }
}
