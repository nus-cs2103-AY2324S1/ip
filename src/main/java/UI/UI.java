package UI;

import java.util.Scanner;

public class UI {
    private static String botName = "cc";
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        System.out.print("Enter a command: ");
        return scanner.nextLine();
    }
}
