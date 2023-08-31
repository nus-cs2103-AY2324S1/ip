package Duke.parser;

import Duke.command.Command;
import Duke.exception.DukeException;
import Duke.message.Message;

import java.util.Scanner;

/**
 * Handles user interactions, input parsing, and displaying messages.
 */
public class Ui {
    Scanner scanner = new Scanner(System.in);
    private final InputParser parser = new InputParser();

    /**
     * Parses the next line of user input into a Command object.
     *
     * @return A Command object corresponding to the parsed user input.
     * @throws DukeException If there's an issue with parsing the input or creating the command.
     */

    public Command ParseLine() throws DukeException {
        return parser.ParseInput(scanner.nextLine());
    }

    /**
     * Checks if there is more user input to be read.
     *
     * @return true if there is more input, false otherwise.
     */
    public boolean HasNext() {
        return scanner.hasNext();
    }
    /**
     * Displays the provided message to the user.
     *
     * @param message The message to be displayed.
     */
    public void ShowMessage(Message message) {
        message.Print();
    }

    /**
     * Displays an error message generated from the provided DukeException.
     *
     * @param e The DukeException that triggered the error.
     */
    public void ShowError(DukeException e) {
        e.generateErrorMessage(e.getMessage()).Print();
    }
}
