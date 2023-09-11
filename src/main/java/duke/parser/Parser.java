package duke.parser;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;


/**
 * Parses user input and generates the appropriate Command objects for the Duke application.
 * This class is responsible for converting user input strings into executable commands.
 */
public class Parser {

    /**
     * Parses the user input string and generates a corresponding Command object.
     *
     * @param userInput The input string provided by the user.
     * @return A Command object representing the action to be executed.
     * @throws DukeException If there's an error while parsing the user input or generating a Command.
     */
    public static Command parse(String userInput) throws DukeException {
        boolean isByePhrase = userInput.equals("bye");
        boolean isHelpPhrase = userInput.equals("help");
        boolean isListPhrase = userInput.equals("list");

        if (isByePhrase) {
            return new ExitCommand();
        } else if (isHelpPhrase) {
            return new HelpCommand();
        } else if (isListPhrase) {
            return new ListCommand();
        } else {
            boolean isMarkPhrase = userInput.contains("mark") && userInput.substring(0, 4).equals("mark");
            if (isMarkPhrase) {
                return getMarkCommand(userInput);
            }

            boolean isUnmarkPhrase = userInput.contains("unmark") && userInput.substring(0, 6).equals("unmark");
            if (isUnmarkPhrase) {
                return getUnmarkCommand(userInput);
            }

            boolean isDeletePhrase = userInput.contains("delete") && userInput.substring(0, 6).equals("delete");
            if (isDeletePhrase) {
                return getDeleteCommand(userInput);
            }

            boolean isFindPhrase = userInput.contains("find") && userInput.substring(0, 4).equals("find");
            if (isFindPhrase) {
                return getFindCommand(userInput);
            }

            boolean isTodo = userInput.contains("todo") && userInput.substring(0, 4).equals("todo");
            if (isTodo) {
                // Add a task
                boolean isEmptyTodo = userInput.equals("todo");
                String message = "OOPS!!! The description of todo cannot be empty.";
                char t = 'T';
                return getAddCommand(isEmptyTodo, message, userInput, t);
            }

            boolean isDeadline = userInput.contains("deadline") && userInput.substring(0, 8).equals("deadline");
            if (isDeadline) {
                // Add a deadline
                boolean isEmptyDeadline = userInput.equals("deadline") || !userInput.contains("/by");
                String message = "OOPS!!! The description of deadline cannot be empty.";
                char d = 'D';
                return getAddCommand(isEmptyDeadline, message, userInput, d);
            }

            boolean isEvent = userInput.contains("event") && userInput.substring(0, 5).equals("event");
            if (isEvent) {
                // Add an event
                boolean isEmptyEvent = userInput.equals("event") || !userInput.contains("/from") || !userInput.contains("/to");
                String message = "OOPS!!! The description of event cannot be empty.";
                char e = 'E';
                return getAddCommand(isEmptyEvent, message, userInput, e);
            }

            displayInvalidStatement();
            return null;
        }
    }

    private static void displayInvalidStatement() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static AddCommand getAddCommand(boolean userInput, String message, String userInput1, char T) throws DukeException {
        if (userInput) { // checks if description is empty
            throw new DukeException(message);
        } else {
            return new AddCommand(userInput1, T);
        }
    }

    private static FindCommand getFindCommand(String userInput) throws DukeException {
        if (!userInput.equals("find")) {
            String searchItem = userInput.substring(5);
            return new FindCommand(searchItem);
        } else {
            throw new DukeException("OOPS!!! The task to be found cannot be empty.");
        }
    }

    private static DeleteCommand getDeleteCommand(String userInput) throws DukeException {
        if (!userInput.equals("delete")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            return new DeleteCommand(index);
        } else {
            throw new DukeException("OOPS!!! Invalid index to be deleted!");
        }
    }

    private static UnmarkCommand getUnmarkCommand(String userInput) throws DukeException {
        if (!userInput.equals("unmark")) {
            try {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Invalid task to be unmarked!");
            }
        } else {
            throw new DukeException("OOPS!!! Invalid task to be unmarked!");
        }
    }

    private static MarkCommand getMarkCommand(String userInput) throws DukeException {
        if (!userInput.equals("mark")) {
            try {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Invalid task to be marked!");
            }
        } else {
            throw new DukeException("OOPS!!! Invalid task to be marked!");
        }
    }
}
