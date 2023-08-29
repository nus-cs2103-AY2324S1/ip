package dook.services;

import java.util.Scanner;

import dook.command.CommandInfo;

/**
 * Responsible for displaying messages to the user, as well as reading user commands.
 */
public class UiDisplay {
    private final Scanner sc;

    public UiDisplay() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user input line and returns it.
     * @return User input string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greetUser() {
        printMessage("Dook here.\nWhat can I do for you?");
    }

    /**
     * Prints the message with two surrounding dividers.
     * @param msg Message to be printed.
     */
    public void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    private void printDivider() {
        System.out.println("_______________________________________");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void bidFarewell() {
        printMessage("Goodbye.");
    }

    /**
     * Displays a full list of possible commands and their effects to the user.
     */
    public void displayHelp() {
        StringBuilder result = new StringBuilder();
        result.append("Available commands:\n");
        for (CommandInfo c : CommandInfo.values()) {
            result.append(c.toString()).append("\n");
        }
        printMessage(result.toString());
    }
}
