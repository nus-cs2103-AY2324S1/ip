package components;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public static final String LINE = "_______________________" +
            "______________________________";

    /**
     * Constructor for Ui class.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the error message.
     */
    public String showError(DukeException e) {
        return e.toString();
    }

    /**
     * Reads the command using Scanner class.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     */
    public String showWelcome(String chatBotName) {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;

        return logo + "\n" + "Hello! I'm " +
                chatBotName + "\n" + "What can I do for you?";
    }

    public String showWelcome(String chatBotName, boolean isGui) {
        return "Hello! I'm " +
                chatBotName + "\n" + "What can I do for you?\n";
    }

    /**
     * Shows the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
