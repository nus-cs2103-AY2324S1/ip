package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    public String readCommand() {
        System.out.print(">> ");
        String input = scanner.nextLine();
        return input;
    }

    public void print(String message) {
        System.out.println("    " + message);
    }
}
