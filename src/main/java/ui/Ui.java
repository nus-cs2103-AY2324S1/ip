package ui;

import java.util.Scanner;

public class Ui {
    /** The scanner for reading the user's input.*/
    private Scanner scanner;

    /**
     * Constructs a new Ui with a scanner.
     */
    public Ui()
    {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a String that is based on the user's input.
     *
     * @return A String representation of the user's input.
     */
    public String getInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints welcome statements then returns a String that is based on the user's input.
     *
     * @return A String representation of the user's input.
     */
    public String getFirstInput() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        return this.getInput();
    }

    /**
     * Prints a farewell message to indicate the end of the program.
     */
    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
