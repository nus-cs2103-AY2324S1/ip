package duke.core;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.command.Command;

/**
 * Class to handle all user interaction.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Responds to the user with a formatted message.
     * 
     * @param message Message to be sent to the user.
     */
    public static <T> void respond(T message) {
        Ui.showSeparator();
        System.out.println(String.format("     %s",  message.toString()));
        Ui.showSeparator();
        Ui.showInputArrow();
    }

    /**
     * Responds to the user with a formatted message.
     * 
     * @param messages Messages to be sent to the user.
     */
    public static <T> void respond(List<T> messages) {
        Ui.showSeparator();
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        Ui.showSeparator();
        Ui.showInputArrow();
    }

    /**
     * Responds to the user with a formatted message.
     * 
     * @param messages Messages to be sent to the user.
     */
    public static <T> void respond(Stream<T> messages) {
        Ui.showSeparator();
        messages.forEach(message -> System.out.println(String.format("     %s",  message.toString())));
        Ui.showSeparator();
        Ui.showInputArrow();
    }

    /** Returns the Command after reading the next line of input from the user. */
    public static Command readCommand() {
        String input = Ui.scanner.nextLine();

        if (input.trim().equals("")) {
            return null;
        }

        return Parser.parseCommand(input);
    }

    public static void showSeparator() {
        System.out.println("    ----------------------------------------------------------------------");
    }

    /** Shows the input arrow for user to input commands. */
    public static void showInputArrow() {
        System.out.print(">>> ");
    }

    /** Shows the greeting message when user starts the program. */
    public static void showGreetMessage() {
        Ui.respond(Stream.of("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)", "What do you want to do today?"));
    }

    /** Shows the exit message when user exits the program. */
    public static void showExitMessage() {
        Ui.respond("Bye. Hope to see you again soon!");
    }

    /** Shows the loading error message when there is an error loading the data file. */
    public static void showLoadingError() {
        Ui.respond("There was an error loading the data file. Please check the file and try again.");
    }
}
