package components;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public static final String LINE = "__________________________________________________________________";

    /**
     * Constructor for Ui class.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the error message.
     * @param e
     */
    public void showError(DukeException e) {
        System.out.println(Ui.LINE);
        System.out.println(e);
        System.out.println(Ui.LINE);
    }

    /**
     * Prints the message.
     */
    public void showLine() {
        System.out.println(Ui.LINE);
    }

    /**
     * Reads the command using Scanner class.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     * @param chatBotName
     */
    public void showWelcome(String chatBotName) {
        System.out.println(Ui.LINE);
        String logo = " ____        _        \n"
               + "|  _ \\ _   _| | _____ \n"
               + "| | | | | | | |/ / _ \\\n"
               + "| |_| | |_| |   <  __/\n"
               + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(Ui.LINE);
    }

    /**
     * Shows the goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Ui.LINE);
    }
}
