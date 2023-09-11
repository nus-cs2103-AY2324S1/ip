package duke.util;

import java.util.Scanner;

/**
 * Represents a user interface for SeeWhyAre bot.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Ui {
    //60 underscores.
    protected static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    protected String printHorizontalLine() {

        return HORIZONTAL_LINE;
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        StringBuilder message = new StringBuilder("\nStarting SeeWhyAre Bot...\n");
        message.append(printHorizontalLine()).append("\nHello! I'm SeeWhyAre Bot!")
                .append("\nWhat can I do for you?")
                .append(printHorizontalLine());
        System.out.println("\nStarting SeeWhyAre Bot...");
        printHorizontalLine();
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
        return message.toString();
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input command.
     */
    public String getUserInput() {
        System.out.println("Enter your Command:");
        return this.scanner.nextLine();
    }

    /**
     * Displays a farewell message to the user and closes the scanner.
     */
    protected String farewell() {
        StringBuilder message = new StringBuilder(printHorizontalLine());
        message.append("\nYou are closing the SeeWhyAre chat bot.")
                .append("\nBye bye. Please use me again soon!")
                        .append(printHorizontalLine());

        printHorizontalLine();
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        printHorizontalLine();
        scanner.close();
        return message.toString();
    }
}
