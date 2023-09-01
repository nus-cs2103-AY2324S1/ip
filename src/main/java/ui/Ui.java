package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui()
    {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        String input = scanner.nextLine();
        return input;
    }

    public String getFirstInput() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        return this.getInput();
    }

    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
