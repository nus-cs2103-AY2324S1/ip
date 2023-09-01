package ui;

import java.util.Scanner;

/**
 * The programme that interacts with the user.
 */
public class Ui {

    /**
     * Returns the user input.
     * @return User input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays welcome message to the user.
     */
    public void welcomeMsg() {
        System.out.println("Hello! I'm Red\nWhat can I do for you?");
    }

}
