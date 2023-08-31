package duke;

import java.util.Scanner;

/**
 * The Parser class is responsible for parsing user input and determining the corresponding actions to take.
 * It provides methods to read user input, check for specific command types, and manage the input scanner.
 */
public class Parser {
    static String userInput;
    static Scanner scanner = new Scanner(System.in);

    /**
     * Retrieves the user input from the scanner.
     *
     * @return The user input as a string.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Sets the user input to a new value.
     *
     * @param newUserInput The new user input value to set.
     */
    public void setUserInput(String newUserInput) {
        userInput = newUserInput;
    }

    /**
     * Checks if the user wants to exit the application.
     *
     * @return True if the user input is "bye", indicating an exit command; false otherwise.
     */
    public boolean bye() {
        return userInput.equals("bye");
    }

    /**
     * Checks if the user wants to list tasks.
     *
     * @return True if the user input is "list", indicating a list command; false otherwise.
     */
    public boolean list() {
        return userInput.equals("list");
    }

    /**
     * Checks if the user wants to mark a task as done.
     *
     * @return True if the user input starts with "mark ", indicating a mark command; false otherwise.
     */
    public boolean mark() {
        return userInput.startsWith("mark ");
    }

    /**
     * Checks if the user wants to unmark a task as done.
     *
     * @return True if the user input starts with "unmark ", indicating an unmark command; false otherwise.
     */
    public boolean unMark() {
        return userInput.startsWith("unmark ");
    }

    /**
     * Checks if the user wants to delete a task.
     *
     * @return True if the user input starts with "delete ", indicating a delete command; false otherwise.
     */
    public boolean delete() {
        return userInput.startsWith("delete ");
    }

    /**
     * Checks if the user wants to add a todo task.
     *
     * @return True if the user input starts with "todo ", indicating a todo command; false otherwise.
     */
    public boolean todo() {
        return userInput.startsWith("todo ");
    }

    /**
     * Checks if the user wants to add a deadline task.
     *
     * @return True if the user input starts with "deadline ", indicating a deadline command; false otherwise.
     */
    public boolean deadline() {
        return userInput.startsWith("deadline ");
    }

    /**
     * Checks if the user wants to add an event task.
     *
     * @return True if the user input starts with "event ", indicating an event command; false otherwise.
     */
    public boolean event() {
        return userInput.startsWith("event ");
    }

    /**
     * Checks if the user wants to find a specific task.
     *
     * @return True if the user input starts with "find ", indicating an event command; false otherwise.
     */
    public boolean find() { return userInput.startsWith("find ");}


    /**
     * Closes the scanner used for reading user input.
     */
    public void goodbye() {
        scanner.close();
    }
}
