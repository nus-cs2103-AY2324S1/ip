package noelPackage.helper;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a User Interface for interacting with the user.
 */
public class Ui {

    Scanner input;

    /**
     * Default constructor that initializes a new Scanner object for standard input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Gets the next line of input from the user.
     * @return The next line of input as a String.
     * @throws NoSuchElementException If no line was found.
     */
    public String getNextLine() {
        if (this.input.hasNextLine()) {
            return this.input.nextLine();
        } else {
            throw new NoSuchElementException("No line found");
        }
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error reading file!");
    }

}