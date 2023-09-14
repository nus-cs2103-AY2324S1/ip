package joi.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.sc.nextLine();
    }

    public void greeting(String name) {
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n");
    }

    public void printToScreen(String output) {
        System.out.println(output);
    }
}
