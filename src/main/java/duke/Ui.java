package duke;

import java.util.Scanner;

/**
 * responsible for printing messages and recieving commands
 */
public class Ui {
    /**
     * scanner to read input
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * read and returns command
     * @return String of user input
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * prints a message to the user
     * @param message String message to be printed
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * greeting to be printed at start of program
     */
    public static void printGreeting() {
        String name = "Chaty";

        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?" + "\n\n");
    }
}
