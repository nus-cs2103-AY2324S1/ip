package linus.util;

import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints the specified message in a specific format.
     * @param message The message to be printed.
     */
    public static void print(String message) {
        System.out.println(
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
                        + message + "\n"
                        + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        );
    }

    /**
     * Prints the welcome message of the chatbot.
     */
    public static void showWelcomeMessage() {
        String name = "LINUS";
        print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }

    /**
     * Prints the exit message of the chatbot.
     */
    public static void showExitMessage() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message of the chatbot.
     */
    public static void showLoadingError() {
        print("The file system experienced an unexpected error.");
    }

    /**
     * Reads the input from the user.
     * @return String
     */
    public static String readInput() {
        return sc.nextLine();
    }
}
