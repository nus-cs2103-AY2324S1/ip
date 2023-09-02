package duke;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Ui is responsible for prompting the user for input.
 * It also contains general methods to output information to the user.
 */
public class Ui {

    private Duke dukeBot;
    public Scanner scannerObj = new Scanner(System.in);

    /**
     * Latest user input is saved in a static field.
     */
    public String userInput;

    public Ui(Duke dukeBot) {
        this.dukeBot = dukeBot;
    }

    /**
     * Prints a standard introduction of Duke bot.
     */
    public static void printIntro() {
        printLine();
        System.out.println("Hello! I'm Roe!\n" + "What can I do for you?\n");
        printLine();
    }

    /**
     * Prints a goodbye message.
     */
    public static void printEnd() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    /**
     * Prints a horizontal line. To be used by different classes.
     */
    public static void printLine() {
        int LINE_LENGTH = 50;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("─");
        }
        System.out.print("\n");
    }

    /**
     * Prints an exception in a predefined format.
     *
     * @param e The exception to be printed.
     */
    public static void showError(DukeException e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }

    /**
     * Repeatedly prompts the user for input until a "bye" is inputted.
     *
     * @return The latest input by the user. To be cached.
     */
    public String promptInput() {
        try {
            do {
                System.out.println();
                userInput = scannerObj.nextLine();
                System.out.println();
            } while (Parser.processInput(userInput, dukeBot) != Parser.Command.BYE);
            printEnd();
        } catch (DukeException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
            promptInput();
        }

        return userInput;
    }
}
