package duke.util;

import java.io.IOException;
import java.util.Scanner;
// Test merge branch for A-Packages

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
    public String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    private Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    protected void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println("\nStarting SeeWhyAre Bot...");
        printHorizontalLine();
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input command.
     */
    public String getUserInput() {
        System.out.println("Enter your Command: ");
        return this.scanner.nextLine();
    }

    /**
     * Displays a farewell message to the user and closes the scanner.
     */
    protected void farewell() {
        printHorizontalLine();
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        printHorizontalLine();
        scanner.close();
    }
}
