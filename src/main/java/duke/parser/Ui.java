package duke.parser;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.message.Message;

/**
 * Handles user interactions, input parsing, and displaying messages.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final InputParser parser = new InputParser();

    /**
     * Parses the next line of user input into a Command object.
     *
     * @return A Command object corresponding to the parsed user input.
     * @throws DukeException If there's an issue with parsing the input or creating the command.
     */

    public Command parseLine() throws DukeException {
        return parser.parseInput(scanner.nextLine());
    }

    /**
     * Checks if there is more user input to be read.
     *
     * @return true if there is more input, false otherwise.
     */
    public boolean hasNext() {
        return scanner.hasNext();
    }
    /**
     * Displays the provided message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(Message message) {
        message.print();
    }

    /**
     * Displays an error message generated from the provided DukeException.
     *
     * @param e The DukeException that triggered the error.
     */
    public void showError(DukeException e) {
        e.generateErrorMessage(e.getMessage()).print();
    }
}
