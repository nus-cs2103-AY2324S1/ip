package joe;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Joe\nWhat can i do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void newLine() {
        System.out.println();
    }

    public void exit() {
        scanner.close();
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}
