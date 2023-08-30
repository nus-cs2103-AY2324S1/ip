package duke.util;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    protected void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to greet the User.
     */
    public void greet() {
        System.out.println("\nStarting SeeWhyAre Bot...");
        printHorizontalLine();
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    public String getUserInput() {
        System.out.println("Enter your Command: ");
        return this.scanner.nextLine();
    }

    /**
     * Function to say goodbye to the User and end the program.
     */
    protected void farewell() {
        printHorizontalLine();
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        printHorizontalLine();
        scanner.close();
    }
}
