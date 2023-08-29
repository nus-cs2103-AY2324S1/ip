package ducky;

import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;

    public UserInterface(Scanner sc) {
        this.sc = sc;
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Ducky and I'm here to help!");
    }

    public void showFarewell() {
        System.out.println("Bye! See you again!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________");
    }

    public void showMessagePerLine(String... args) {
        for (String a : args) {
            System.out.println(a);
        }
    }
}