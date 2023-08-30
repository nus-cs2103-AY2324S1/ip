package Helper;

import java.util.Scanner;

public class Ui {
    public static String divider = "____________________________________________________________";

    /**
     * Prints out a welcome message to the User to signal the ChatBot has started up.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        showLine();
    }

    /**
     * Prints out a dividing line.
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Wraps a message between two dividing lines and prints out the message.
     * @param message
     */
    public void print(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Reads the User input through the Command Line Interface.
     * @return String of User input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        return input;
    }
}
