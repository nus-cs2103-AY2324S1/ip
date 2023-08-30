package Ui;

import java.util.Scanner;

public class Ui {
    private static String botName = "cc";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
