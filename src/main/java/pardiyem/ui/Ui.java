package pardiyem.ui;

import java.util.Scanner;

public class Ui {
    static final String WELCOME = "Salve, I'm Pardi\nWhat can I do for you?";
    static final String BYE = "Ciao! See you again!";

    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        showDivider();
        System.out.println(WELCOME);
        showDivider();
    }

    public void showFarewell() {
        System.out.println(BYE);
    }

    public void showDivider() {
        System.out.println("-------------------------");
    }

    public void showOutput(String s) {
        System.out.println(s);
    }

    public String readCommand() throws IllegalArgumentException{
        if (!scanner.hasNext()) {
            throw new IllegalArgumentException("Out of commands, are we?");
        }
        return scanner.nextLine();
    }

    public void showException(Exception e) {
        System.out.println(e.toString());
    }
}
