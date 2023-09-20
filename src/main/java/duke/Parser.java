package duke;

import java.util.Scanner;

/**
 * The Parser class is responsible for parsing user input and determining the corresponding actions to take.
 * It provides methods to read user input, check for specific command types, and manage the input scanner.
 */
public class Parser {
    private static String userInput;
    private static final Scanner scanner = new Scanner(System.in);
    private final Ui ui = new Ui();

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
    public boolean isBye() {
        return userInput.equals("bye");
    }

    /**
     * Checks if the user wants to list tasks.
     *
     * @return True if the user input is "list", indicating a list command; false otherwise.
     */
    public boolean isList() {
        return userInput.equals("list");
    }

    /**
     * Checks if the user wants to mark a task as done.
     *
     * @return True if the user input starts with "mark ", indicating a mark command; false otherwise.
     */
    public boolean isMark() {
        return userInput.startsWith("mark ");
    }

    /**
     * Checks if the user wants to unmark a task as done.
     *
     * @return True if the user input starts with "unmark ", indicating an unmark command; false otherwise.
     */
    public boolean isUnmark() {
        return userInput.startsWith("unmark ");
    }

    /**
     * Checks if the user wants to delete a task.
     *
     * @return True if the user input starts with "delete ", indicating a delete command; false otherwise.
     */
    public boolean isDelete() {
        return userInput.startsWith("delete ");
    }

    /**
     * Checks if the user wants to add a todo task.
     *
     * @return True if the user input starts with "todo ", indicating a todo command; false otherwise.
     */
    public boolean isTodo() {
        return userInput.startsWith("todo ");
    }

    /**
     * Checks if the user wants to add a deadline task.
     *
     * @return True if the user input starts with "deadline ", indicating a deadline command; false otherwise.
     */
    public boolean isDeadline() {
        return userInput.startsWith("deadline ");
    }

    /**
     * Checks if the user wants to add an event task.
     *
     * @return True if the user input starts with "event ", indicating an event command; false otherwise.
     */
    public boolean isEvent() {
        return userInput.startsWith("event ");
    }

    /**
     * Checks if the user wants to find a specific task.
     *
     * @return True if the user input starts with "find ", indicating an event command; false otherwise.
     */
    public boolean isFind() {
        return userInput.startsWith("find ");
    }

    /**
     * Checks if the user wants to find a specific task.
     *
     * @return True if the user input starts with "find ", indicating an event command; false otherwise.
     */
    public boolean isSort() {
        return userInput.equals("sort");
    }


    /**
     * Closes the scanner used for reading user input.
     */
    public void goodbye() {
        scanner.close();
    }

    /**
     * Parses user input to execute various commands and provides a corresponding response.
     *
     * @param tasks The task list to be manipulated based on the parsed command.
     * @return A response message or result of executing the parsed command.
     */
    public String parse(TaskList tasks) {
        try {
            if (this.isBye()) {
                goodbye();
                return ui.goodbye();
            } else if (this.isList()) {
                return tasks.printFileContents();
            } else if (this.isSort()) {
                return tasks.returnSortedList();
            } else if (this.isMark()) {
                return tasks.mark(userInput);
            } else if (this.isUnmark()) {
                return tasks.unMark(userInput);
            } else if (this.isDelete()) {
                return tasks.delete(userInput);
            } else if (this.isTodo()) {
                return tasks.handleTodo(userInput);
            } else if (this.isDeadline()) {
                return tasks.handleDeadline(userInput);
            } else if (this.isEvent()) {
                return tasks.handleEvent(userInput);
            } else if (this.isFind()) {
                return tasks.handleFind(userInput);
            } else {
                throw new DukeException("Error: Invalid Command!");
            }
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }
}
