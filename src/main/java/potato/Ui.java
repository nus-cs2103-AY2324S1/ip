package potato;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hey GURL I'm Potato\n" + "How can I help ya?");
    }

    public void showGoodbye() {
        System.out.println("You're cancelled. Leave.");
    }

    public void showLine() {
        System.out.println("-----------------------------------------");
    }

    public void showLoadingError() {
        System.out.println("Oops there's nothing to load! Let's start a new list!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
